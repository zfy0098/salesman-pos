package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.model.LoginModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.MakeCipherText;
import com.rhjf.salesman.utils.UtilsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hadoop on 2017/10/23.
 *
 * @author hadoop
 */
@Service("VerifyPasswordService")
@Transactional
public class VerifyPasswordService {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Value("${protectINDEX}")
    private String protectINDEX;

    public void verifyPassword(SalesmanLogin user , Map params , ResponseData response){

        try {


        LoginModel loginModel = UtilsConstant.mapToBean(params , LoginModel.class);

        String loginPWD = loginModel.getLoginPWD();

        MakeCipherText makeCipherText = new MakeCipherText();

        String passwd = makeCipherText.calLoginPwd(loginModel.getLoginID(),user.getPassword(), loginModel.getSendTime() , protectINDEX);

        if(!passwd.equals(loginModel.getLoginPWD())){
            log.info("用户" + user.getLoginID() + "密码错误, 上送密码：" + loginPWD + ", 平台计算密码:" + passwd);
            response.setRespCode(RespCode.PasswordError[0]);
            response.setRespDesc(RespCode.PasswordError[1]);
            return;
        }else{
            log.info("用户" + user.getLoginID() + "密码正确：" + loginPWD);
        }

        response.setRespCode(RespCode.SUCCESS[0]);
        response.setRespDesc(RespCode.SUCCESS[1]);
        }catch (Exception e){
            log.error("验证密码错误:" , e);
            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);
        }

    }
}
