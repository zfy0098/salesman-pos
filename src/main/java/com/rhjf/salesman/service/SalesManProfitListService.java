package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.PosOrderDB;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesManProfitModel;
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
 * Created by hadoop on 2017/10/23.
 *
 * @author hadoop
 */
@Service("SalesManProfitListService")
public class SalesManProfitListService {


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private PosOrderDB posOrderDB;

    public void SalesManProfitList(SalesmanLogin user, Map params, ResponseData response) {

        try {
            SalesManProfitModel salesManProfitModel = UtilsConstant.mapToBean(params, SalesManProfitModel.class);


            List<Map<String,Object>> saleManProfitList = posOrderDB.saleManProfitList(null);


            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(DateUtil.yyyy_MM_ddHH_mm_ss));
            JSONArray array = JSONArray.fromObject(saleManProfitList,jsonConfig);


            response.setProfitTotal("200");

            response.setList(array.toString());

            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);

        } catch (Exception e) {

        }


    }

}
