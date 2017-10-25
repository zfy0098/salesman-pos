package com.rhjf.salesman.db;

import com.rhjf.salesman.utils.UtilsConstant;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 2017/10/25.
 *
 * @author hadoop
 */
@Repository
public class PosDB extends  DBBase{




    public List<String> customerSNList(Object[] obj){
        String sql = "select * from pos where customer_id=?";
        List<Map<String,Object>> list = jdbc.queryForList(sql , obj);
        List<String> snList = new ArrayList<String>();

        for (int i = 0 ; i < list.size() ; i++){
            snList.add(UtilsConstant.ObjToStr(list.get(i).get("MFG_SN")));
        }
        return snList;
    }



    public Map<String,Object> snExists(Object[] obj){
        String sql = "select ID , MFG_SN from pos where customer_id is null and mfg_sn=? ";

        System.out.println(sql + "-- "+ Arrays.toString(obj));

        return queryForMap(sql , obj);
    }

}
