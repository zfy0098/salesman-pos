package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.SmsCodeDB;
import com.rhjf.salesman.model.LoginModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.MakeCipherText;
import com.rhjf.salesman.utils.UtilsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hadoop on 2017/10/23.
 *
 * @author hadoop
 */
@Service("VerifySmsCodeService")
@Transactional
public class VerifySmsCodeService {


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SmsCodeDB smsCodeDB;

    public void verifySmsCode(SalesmanLogin user , Map params , ResponseData response){

        MakeCipherText makeCipherText = new MakeCipherText();

        LoginModel loginModel;
        try{
            loginModel = UtilsConstant.mapToBean(params , LoginModel.class);
        }catch (Exception e){
            return ;
        }

        String loginID = user.getLoginID();
        String smsCode = loginModel.getSmsCode();
        Map<String,Object> codeMap = smsCodeDB.getSmsCode(new Object[]{loginID});

        if(codeMap == null || codeMap.isEmpty()){

            log.info("用户：" + user.getLoginID() + "查询验证码数据为空，提示用户从新获取");

            response.setRespCode(RespCode.SMSCodeError[0]);
            response.setRespDesc("验证已经失效，请重新获取");
            return ;
        }

        log.info("验证新增用户的手机验证码是否正确:"+ user.getLoginID() + ",输入验证码:" + smsCode +
                " , 系统保存验证码：" + codeMap.get("smsCode"));


        if(codeMap != null && smsCode.equals(codeMap.get("SMSCODE"))){

            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);

            smsCodeDB.delSmsCode(new Object[]{loginID});

        } else{
            response.setRespCode(RespCode.SMSCodeError[0]);
            response.setRespDesc(RespCode.SMSCodeError[1]);
        }
    }
}
