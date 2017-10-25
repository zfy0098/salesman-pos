package com.rhjf.salesman.db;

import com.rhjf.salesman.model.SalesmanKey;
import com.rhjf.salesman.utils.UtilsConstant;
import org.springframework.stereotype.Repository;

import java.net.StandardSocketOptions;
import java.util.Map;

/**
 * Created by hadoop on 2017/9/26.
 */

@Repository
public class SalesmanKeyDB extends DBBase{


    /**
     *   获取业务员秘钥信息
     * @param salesmanID
     * @return
     */
    public SalesmanKey salesmanKeyInfo(Integer salesmanID){
        String sql = "select * from SALESMAN_KEY where SALESMANID=?";
        Map<String,Object> map = queryForMap(sql , new Object[]{salesmanID});

        if(map == null || map.isEmpty()){
            return null;
        }else{
            try {
                return UtilsConstant.mapToBean(map , SalesmanKey.class);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     *     添加秘钥
     * @param obj
     * @return
     */
    public int addTermKey(Object[] obj){

        String sql = "insert into SALESMAN_KEY (ID,SALESMANID ,TMKKEY , TERMTMKKEY)" +
                " VALUES (SEQ_SALESMAN_KET_ID.nextval ,? , ? , ?)";

        return jdbcTemplate.update(sql , obj);
    }


    /**
     *    更新秘钥信息
     * @param obj
     * @return
     */
    public int updateMacKey(Object[] obj){
        String sql = "update SALESMAN_KEY set MACKEY = ? where SALESMANID=?";
        return jdbcTemplate.update(sql , obj);
    }

}
