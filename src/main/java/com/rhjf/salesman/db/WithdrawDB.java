package com.rhjf.salesman.db;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by hadoop on 2017/9/29.
 */
@Repository
public class WithdrawDB extends DBBase{


    public int tx(Integer userid , String money , String termSerno ){
        String sql = "insert into WITHDRAW (ID ,applyMoney,applyUserID,applyDate,termserno) " +
                "VALUES( SEQ_WITHDRAW_ID.nextval , ?,?,sysdate,?)";

        jdbc.update(sql, new Object[] {money ,userid , termSerno });


        String updateFeebalance = "update SALESMAN_LOGIN SET FEEBALANCE=FEEBALANCE-? WHERE ID=?";
        jdbc.update(updateFeebalance , new Object[]{money , userid});

        return 1;

    }


    public List<Map<String,Object>> txlist(Object[] obj){
        String sql = "select * from WITHDRAW where applyUserID = ?";
        return jdbc.queryForList(sql , obj);
    }

}
