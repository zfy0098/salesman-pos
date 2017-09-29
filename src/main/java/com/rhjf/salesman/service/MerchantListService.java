package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.InternetMerchantDB;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 *   商户列表
 * Created by hadoop on 2017/9/27.
 */
@Service("merchantList")
public class MerchantListService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InternetMerchantDB internetMerchantDB;

    public void merchantList(SalesmanLogin user , Map params , ResponseData response){


        List<Map<String , Object>> list = internetMerchantDB.merchantList(user.getID());
        response.setList(JSONArray.fromObject(list).toString());
        response.setRespCode(RespCode.SUCCESS[0]);
        response.setRespDesc(RespCode.SUCCESS[1]);

    }
}
