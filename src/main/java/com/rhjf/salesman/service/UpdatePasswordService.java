package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.SalesmanLoginDB;
import com.rhjf.salesman.model.LoginModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.MakeCipherText;
import com.rhjf.salesman.utils.UtilsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hadoop on 2017/9/28.
 */
@Service("UpdatePasswordService")
@Transactional
public class UpdatePasswordService {


    @Autowired
    private SalesmanLoginDB salesmanLoginDB;


    @Value("${protectINDEX}")
    private String protectINDEX;

    public void updatePassword(SalesmanLogin user , Map params , ResponseData response){


        try{
            LoginModel loginModel = UtilsConstant.mapToBean(params , LoginModel.class);


            MakeCipherText makeCipherText = new MakeCipherText();
            String password = makeCipherText.MakeLoginPwd(loginModel.getLoginPWD(), protectINDEX);
            int x = salesmanLoginDB.updateSalesmanLoginPWD(new Object[]{password , loginModel.getLoginID() });

            if(x > 0){
                response.setRespCode(RespCode.SUCCESS[0]);
                response.setRespDesc(RespCode.SUCCESS[1]);
            }else{
                response.setRespCode(RespCode.ServerDBError[0]);
                response.setRespDesc(RespCode.ServerDBError[1]);
            }
        }catch (Exception e){

            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);
        }
    }
}
