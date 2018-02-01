package com.rhjf.salesman.db;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 2017/10/18.
 */
@Repository
public class PosOrderDB extends DBBase {


    /**
     *   查询商户为业务员创造的收益列表
     * @param obj
     * @return
     */
    public List<Map<String ,Object>> profltList(Object[] obj){

        String sql = "select a.* from pos_order  a left join " +
                "(select * from pos_order_break_each where owner_role='SALEMAN')  b " +
                "on a.id = b.order_id " +
                " where  a.status='SUCCESS' and  CUSTOMER_NO = ?";
        return jdbc.queryForList(sql , obj);
    }


    /**
     *   查询收益总和
     * @param obj
     * @return
     */
    public double profitAmount(Object[] obj){
        String sql = "select nvl(sum(PROFIT) , 0) as amount " +
                " from pos_order_break_each where  owner_role='SALEMAN' and CUSTOMER_NO=? ";
        Double profit = jdbc.queryForObject(sql , obj , Double.class);
        return profit;
    }



    public List<Map<String,Object>> saleManProfitList(Object[] obj){
        String sql = "select po.amount as amount , nvl(pobe.profit ,0) as  profit , pobe.status , case when pobe.status='SUCCESS' then 'PURCHASE' " +
                " when pobe.status='REPEAL' then 'PURCHASE_REVERSAL' when pobe.status='REVERSALED' then 'PURCHASE_REVERSAL' end  as TRANS_TYPE   , ctm.short_name as short_name , po.create_time as create_time " +
                " from pos_order  po inner join customer  ctm on po.customer_no=ctm.customer_no" +
                " left join (select profit, order_id , TRANS_TYPE , STATUS from pos_order_break_each where owner_role='SALEMAN' and OWNER_NO=? ) pobe on po.id=pobe.order_id  " +
                " where trunc(po.create_time)>=to_date(?,'yyyymmdd') and  trunc(po.create_time)<=to_date(?,'yyyymmdd') and ctm.SALEMAN_ID=? " +
                " and po.status='SUCCESS' order by po.create_time desc  ";
        return jdbc.queryForList(sql , obj);
    }


    public double saleManProfitTotal(Object[] obj){
        String sql = "select  nvl(sum(pobe.profit) ,0) as  profit  " +
                " from pos_order  po inner join customer  ctm on po.customer_no=ctm.customer_no" +
                " left join (select profit, order_id from pos_order_break_each where owner_role='SALEMAN' ) pobe on po.id=pobe.order_id  " +
                " where trunc(po.create_time)>=to_date(?,'yyyymmdd') and  trunc(po.create_time)<=to_date(?,'yyyymmdd') and ctm.SALEMAN_ID=?  and po.status='SUCCESS' ";

        Double profit = jdbc.queryForObject(sql , obj , Double.class);

        return profit;
    }
}
