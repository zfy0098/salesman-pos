package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.CustomerDB;
import com.rhjf.salesman.db.InternetMerchantDB;
import com.rhjf.salesman.model.MerchantModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.DateJsonValueProcessor;
import com.rhjf.salesman.utils.DateUtil;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 *
 *   商户列表
 * Created by hadoop on 2017/9/27.
 *
 * @author hadoop
 *
 */
@Service("merchantList")
public class MerchantListService {


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CustomerDB customerDB;

    public void merchantList(SalesmanLogin user , Map params , ResponseData response){

        try {

            MerchantModel merchantModel = UtilsConstant.mapToBean(params , MerchantModel.class);

            List<Map<String , Object>> list = customerDB.merchantList(new Object[]{user.getSalesmanID() , "%" +UtilsConstant.ObjToStr(merchantModel.getMerchantName())+ "%"});


            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(DateUtil.yyyy_MM_dd));
            JSONArray array = JSONArray.fromObject(list,jsonConfig);

            response.setList(array.toString());
            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);
        }catch (Exception e){

        }
    }
}
