package com.rhjf.salesman.db;

import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.ObjectMapUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hadoop on 2017/9/26.
 */
@Repository
public class SalesmanLoginDB extends DBBase{


    /**
     *   获取业务员信息
     * @param loginID
     * @return
     */
    public SalesmanLogin salesmanInfo(String loginID){
        String sql = "select * from SALESMAN_LOGIN where LOGINID=?";
        Map<String,Object> map = queryForMap(sql , new Object[]{loginID});

        if(map == null || map.isEmpty()){
            return null;
        }else{
            try {
                return ObjectMapUtils.mapToObject(map , SalesmanLogin.class);
            }catch (Exception e){
                return null;
            }
        }
    }


    /**
     *   更细业务员登录信息
     * @param obj
     * @return
     */
    public int updateSalesmanLoginInfo(Object[] obj){

        String sql = "update salesman_login set lastlogintime = sysdate , loginpsn=? where id=?";

        return jdbc.update(sql  ,  obj);
    }



    public int updateSalesmanLoginPWD(Object[] obj){
        String sql = "update salesman_login set password=? where loginid=?";
        return jdbc.update(sql , obj);
    }





}
