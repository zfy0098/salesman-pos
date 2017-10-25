package com.rhjf.salesman.db;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by hadoop on 2017/10/23.
 *
 * @author hadoop
 */
@Repository
public class BinverifyDB extends DBBase{



    public Map<String ,Object> bankBin(String bankCardNo){
        String sql = "select * from tab_pay_binverify where " +
                "verifyCode = SUBSTR(?,1,verifyLength)";


        return queryForMap(sql , new Object[]{bankCardNo});

    }

}
