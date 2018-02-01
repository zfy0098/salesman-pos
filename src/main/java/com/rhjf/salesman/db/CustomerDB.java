package com.rhjf.salesman.db;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 2017/10/17.
 */
@Repository
public class CustomerDB extends  DBBase{


    /**
     *   业务员查询商户列表
     * @param obj
     * @return
     */
    public List<Map<String ,Object>> merchantList(Object [] obj){
        String sql = "select a.SHORT_NAME , a.creation  , a.STATUS , a.CUSTOMER_NO , b.legal_contact " +
                "from customer a inner join customer_detail  b on a.id=b.customer_id " +
                "where a.saleman_id=? and a.SHORT_NAME like ? ORDER  by a.creation desc ";
        return jdbc.queryForList(sql , obj);
    }

    public Map<String,Object> customerInfo(Object[] obj){
        String sql = "select * from Customer where customer_no=?";
        return queryForMap(sql , obj);
    }

}
