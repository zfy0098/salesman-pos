package com.rhjf.salesman.db;

import com.rhjf.salesman.model.Appversion;
import com.rhjf.salesman.utils.UtilsConstant;
import org.springframework.stereotype.Repository;

/**
 * Created by hadoop on 2017/10/25.
 *
 * @author hadoop
 */
@Repository
public class AppversionDB extends DBBase{



    public Appversion appversionInfo(String deviceType) throws  Exception{

        String sql = "select * from APPVERSION where DEVICETYPE=?";

        Appversion appversion = UtilsConstant.mapToBean(queryForMap(sql , new Object[]{deviceType}) , Appversion.class);
        return appversion;
    }

}
