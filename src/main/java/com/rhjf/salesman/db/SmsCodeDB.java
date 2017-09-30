package com.rhjf.salesman.db;

import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * Created by hadoop on 2017/9/30.
 */
@Repository
public class SmsCodeDB extends  DBBase{

    public int saveSmsCode(Object[] obj){
        String sql = "insert into SMSCODE (id,PHONENUM , SMSCODE , INSERTTIME)" +
                " values (SEQ_SMSCODE_ID.nextval , ? , ? , sysdate)";
        return jdbc.update(sql , obj);
    }



    public Map<String,Object> getSmsCode(Object[] obj){

        String sql = "select * from SMSCODE where PHONENUM=? ORDER BY INSERTTIME DESC";
        return queryForMap(sql , obj);
    }


    public int delSmsCode(Object[] obj){
        String sql = "delete from SMSCODE where PHONENUM=?";

        return jdbc.update(sql , obj);
    }
}
