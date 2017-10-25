package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.SmsCodeDB;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.SmsUtil;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hadoop on 2017/9/30.
 *
 *
 * @author hadoop
 *
 */
@Service("SendSmsCodeService")
public class SendSmsCodeService {


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Value("${ZhuCe}")
    private String zhuce;

    @Value("${SMSCODEAPPID}")
    private String smsCodeAppid;

    @Autowired
    private SmsCodeDB smsCodeDB;

    public void sendSmsCode(SalesmanLogin user , Map params , ResponseData response){

        log.info("商户手机号：" + user.getLoginID() + "获取验证码");


        String smsCode = UtilsConstant.GetSmsCode();


        int nRet = smsCodeDB.saveSmsCode(new Object[]{user.getLoginID() , smsCode});
        if(nRet < 0){
            log.info("记录手机号校验码失败，手机号=【"+user.getLoginID()+"】");
            response.setRespCode("96");
            response.setRespDesc("手机号校验失败");
            return ;
        }
        //发送短信
        JSONObject json = SmsUtil.sendSMS(user.getLoginID() , smsCode, zhuce , "5" , smsCodeAppid);
        if(json.getInt("code")==0){
            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc("短信已发送");
        }else{
            response.setRespCode("96");
            response.setRespDesc(json.getString("message"));
        }
    }
}
