package com.rhjf.salesman.db;

import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * Created by hadoop on 2017/10/24.
 *
 * @author hadoop
 */
@Repository
public class CustomerDetailDB extends DBBase{



    public int updateCustomerPhoto(Object[] obj){
        String sql = "update CUSTOMER_DETAIL set ID_PHOTO=? , LICENSE_PHOTO=?,SPOT_PHOTO=?,OPENING_PHOTO=?,OTHER_PHOTO=? , BANNER_PHOTO=? , COUNTER_PHOTO=?  " +
                " , OWNER_PHOTO =?  where CUSTOMER_ID=?";
        return jdbc.update(sql , obj);
    }


    public Map<String,Object> customerDetailInfo(Object[] obj){
        String sql = "select * from Customer_Detail where CUSTOMER_ID=?";
        return queryForMap(sql , obj);
    }


}
