package com.rhjf.salesman.db;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by hadoop on 2017/9/29.
 */
@Repository
public class WithdrawDB extends DBBase{


    /**
     *  业务员执行提现操作
     * @param userid     提现业务员salesman_login表id
     * @param money      提现金额
     * @param termSerno
     * @param accountNo
     * @return
     */
    public int tx(Integer userid , Double money , String termSerno , String accountNo ){
        String sql = "insert into WITHDRAW (ID ,applyMoney,applyUserID,applyDate,termserno,ACCOUNTNO) " +
                "VALUES( SEQ_WITHDRAW_ID.nextval , ?,?,sysdate,?,?)";

        jdbc.update(sql, new Object[] {money ,userid , termSerno , accountNo });


        String updateFeeBalance = "update SALESMAN_LOGIN SET FEEBALANCE=FEEBALANCE-? WHERE ID=?";
        jdbc.update(updateFeeBalance , new Object[]{money + 1 , userid});

        return 1;

    }


    /**
     *   提现记录
     * @param obj
     * @return
     */
    public List<Map<String,Object>> txlist(Object[] obj){
        String sql = "select applymoney , applydate , bankcode , bankname , accountno  " +
                " from WITHDRAW a left join tab_pay_binverify b on b.verifyCode = SUBSTR(a.accountno,1,b.verifyLength) " +
                " where a.applyuserid=? and to_number(to_char(a.applydate , 'YYYYmm'))=?";
        return jdbc.queryForList(sql , obj);
    }



    public Double txTotalAmount(Object[] obj){
        String sql = "select sum(applymoney) " +
                " from WITHDRAW  " +
                " where applyuserid=? and to_number(to_char(applydate , 'YYYYmm'))=?";

        Double amount = jdbc.queryForObject(sql , obj , Double.class);
        return amount;
    }


}
