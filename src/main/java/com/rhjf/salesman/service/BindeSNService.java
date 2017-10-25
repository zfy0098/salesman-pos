package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.Constants;
import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.CustomerDB;
import com.rhjf.salesman.db.PosDB;
import com.rhjf.salesman.model.BindeSNModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.HttpClient;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hadoop on 2017/10/20.
 *
 * @author hadoop
 *
 *  商户绑定sn号码
 *
 */
@Service("BindeSNService")
@Transactional
public class BindeSNService {


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private PosDB posDB;

    @Value("${BIND_SN_URL}")
    private String bindSnURL;

    @Autowired
    private CustomerDB customerDB;


    public void BindeSN(SalesmanLogin user , Map params , ResponseData response){

        try {
            BindeSNModel bindeSNModel = UtilsConstant.mapToBean(params , BindeSNModel.class);
            Map<String,Object> snmap = posDB.snExists(new Object[]{bindeSNModel.getTerminal()});

            if(snmap == null || snmap.isEmpty()){
                log.info("sn 号：" + bindeSNModel.getTerminal() + "已经绑定过 , 或不存在");
                response.setRespCode(RespCode.SNERROR[0]);
                response.setRespDesc(RespCode.SNERROR[1]);
                return ;
            }

            Map<String,Object> customerMap = customerDB.customerInfo(new Object[]{bindeSNModel.getCustomerNo()});

            Map<String, Object> map = new HashMap<>(16);

            map.put("cusinput" , customerMap.get("ID"));
            map.put("posinput" , snmap.get("ID"));
            map.put("operate" , 1);

            log.info("sn 绑定请求报文：" + map.toString());
            String result = HttpClient.post(bindSnURL , map , "1");

            log.info("sn  绑定 想用报文：" + result);

            JSONObject json = JSONObject.fromObject(result);

            JSONObject data = json.getJSONObject("data");

            Integer respCode = data.getInt("stateCode");


            if(0 == respCode){
                response.setRespCode(RespCode.SUCCESS[0]);
                response.setRespDesc(RespCode.SUCCESS[1]);
            }else {
                response.setRespCode(RespCode.SNERROR[0]);
                response.setRespDesc(data.getString("mesg"));
            }

            return ;
        }catch (Exception e){
            log.error("绑定异常" , e);
            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);
        }

    }
}
