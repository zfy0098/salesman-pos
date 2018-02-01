package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.CustomerDB;
import com.rhjf.salesman.db.CustomerDetailDB;
import com.rhjf.salesman.db.PosDB;
import com.rhjf.salesman.db.PosOrderDB;
import com.rhjf.salesman.model.BeanBase;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 *   查询商户为业务员创造的收益
 *
 * Created by hadoop on 2017/10/18.
 *
 * @author hadoop
 *
 */
@Service("QueryMerchantTotalAmountService")
public class QueryMerchantTotalAmountService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PosOrderDB posOrderDB;


    @Autowired
    private CustomerDB customerDB;

    @Autowired
    private PosDB posDB;


    @Autowired
    private CustomerDetailDB customerDetailDB;


    public void queryMerchantTotalAmount(SalesmanLogin user , Map params , ResponseData response){

        try{
            BeanBase queryMerchantTotalAmountModel = UtilsConstant.mapToBean(params , BeanBase.class);

            Map<String,Object> customerMap = customerDB.customerInfo(new Object[]{queryMerchantTotalAmountModel.getCustomerNo()});

            List<String> snList = posDB.customerSNList(new Object[]{customerMap.get("ID")});

            Double profit = posOrderDB.profitAmount(new Object[]{queryMerchantTotalAmountModel.getCustomerNo()});

            Map<String,Object> customerDetailMap = customerDetailDB.customerDetailInfo(new Object[]{customerMap.get("ID")});


            response.setTotal(String.valueOf(profit));
            response.setMessage(UtilsConstant.ObjToStr(customerDetailMap.get("LINES")));

            response.setList(JSONArray.fromObject(snList).toString());
            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);
        } catch (Exception e){
            log.error("查询商户交易数据异常：" + e.getMessage() , e);

            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);

        }
    }
}
