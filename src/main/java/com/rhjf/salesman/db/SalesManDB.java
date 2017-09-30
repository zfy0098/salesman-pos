package com.rhjf.salesman.db;

import com.rhjf.salesman.model.SalesMan;
import com.rhjf.salesman.utils.UtilsConstant;
import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * Created by hadoop on 2017/9/29.
 */
@Repository
public class SalesManDB extends  DBBase{




    public SalesMan getSalesMan(Integer id)  throws Exception {
        String sql = "select * from SALESMAN where id=?";
        Map<String,Object> map = queryForMap(sql , new Object[]{id});

            SalesMan salesMan = UtilsConstant.mapToBean(map , SalesMan.class);
            return salesMan;

    }


    public int updateBankNo(Object[] obj){
        String sql = "update SALESMAN set BANKNO=? where id=?";
        return jdbc.update(sql , obj);
    }


}
