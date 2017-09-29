package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.Constants;
import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.SalesmanKeyDB;
import com.rhjf.salesman.db.SalesmanLoginDB;
import com.rhjf.salesman.model.LoginModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanKey;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 *
 *   业务员登录接口
 * Created by hadoop on 2017/9/26.
 */
@Service("loginService")
@Transactional
public class LoginService {

    private Logger log = LoggerFactory.getLogger(this.getClass());



    @Autowired
    private SalesmanKeyDB salesmanKeyDB;

    @Autowired
    private SalesmanLoginDB salesmanLoginDB;

    @Value("${initKey}")
    private String initKey;

    @Value("${protectINDEX}")
    private String protectINDEX;

    public void login(SalesmanLogin user, Map<String,Object> map , ResponseData response){

        LoginModel loginModel ;
        try{
            loginModel = ObjectMapUtils.mapToObject(map , LoginModel.class);
        }catch (Exception e){
            log.error("转换实体类失败:" , e);
            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);
            return ;
        }

        MakeCipherText makeCipherText = new MakeCipherText();
        String pwd = makeCipherText.calLoginPwd(loginModel.getLoginID(),user.getPassword() , loginModel.getSendTime() , protectINDEX);

        /**  登录密码错误 **/
        if(!loginModel.getLoginPWD().equals(pwd)){

            log.info("用户:" + user.getLoginID() + "登录密码错误. 用户上传：" + loginModel.getLoginPWD() + "平台计算:" + pwd);

            response.setRespCode(RespCode.PasswordError[0]);
            response.setRespDesc(RespCode.PasswordError[1]);
            return ;
        }else{

            user.setLastLoginTime(DateUtil.getNowTime(DateUtil.yyyyMMddHHmmss));
            user.setLoginPSN(loginModel.getTerminalInfo());
            salesmanLoginDB.updateSalesmanLoginInfo(new Object[]{loginModel.getTerminalInfo() , user.getID()});
            SalesmanKey termKey = salesmanKeyDB.salesmanKeyInfo(user.getSalesmanID());
            if(termKey == null){
                log.info("用户：" + user.getLoginID() + "秘钥配置为空，将重新分配秘钥信息");

                /** 为用户分配秘钥  **/
                String termTmkKey = MD5.md5(UtilsConstant.getUUID(), "UTF-8").toUpperCase();
                String tmkKey = MD5.md5(UtilsConstant.getUUID(), "UTF-8").toUpperCase();

                salesmanKeyDB.addTermKey(new Object[]{ user.getID() , tmkKey , termTmkKey });
            }

            try {
                String tmk = DESUtil.bcd2Str(DESUtil.decrypt3(termKey.getTermTmkKey(), initKey));
                response.setSecretKey(tmk);
                response.setRespCode(RespCode.SUCCESS[0]);
                response.setRespDesc(RespCode.SUCCESS[1]);
            } catch (Exception e) {
                log.error("登录转换秘钥错误:" + e.getMessage()  ,  e);
            }
        }
    }
}
