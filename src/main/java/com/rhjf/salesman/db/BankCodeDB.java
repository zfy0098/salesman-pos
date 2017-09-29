package com.rhjf.salesman.db;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 2017/9/28.
 */
@Repository
public class BankCodeDB extends DBBase{


    /**
     *   获取银行信息
     * @param bankbranchs
     * @return
     */
    public Map<String,Object> getBankCodeInfo(String bankbranchs){

        String sql = "select * from bankcode where bankbranchs = ?";
        return queryForMap(sql , new Object[]{bankbranchs});

    }


    public List<String> getBankBranchs(Object[] obj){
        String sql = "select bankbranchs from bankcode  t " +
                " where ? like CONCAT(t.BANKPROVS ,'%') AND ? like CONCAT(t.BANKCITYS ,'%') AND BANKNAMES LIKE CONCAT(CONCAT('%'  , ?) , '%')";

        List<Map<String,Object>> list = jdbc.queryForList(sql , obj);

        List<String> bankbranchslist = new ArrayList<>();
        for (Map<String,Object>  map : list) {
            bankbranchslist.add(map.get("bankbranchs").toString());
        }
        return bankbranchslist;
    }


}
