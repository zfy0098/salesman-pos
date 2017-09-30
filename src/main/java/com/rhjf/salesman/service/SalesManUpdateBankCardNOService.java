package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.SalesManDB;
import com.rhjf.salesman.model.MerchantModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesMan;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.AuthUtil;
import com.rhjf.salesman.utils.UtilsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by hadoop on 2017/9/29.
 */
public class SalesManUpdateBankCardNOService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesManDB salesManDB;

    public void SalesManUpdateBankCardNO(SalesmanLogin user , Map params , ResponseData response){


        SalesMan salesman;
        MerchantModel merchantModel ;
        try{
            salesman = salesManDB.getSalesMan(user.getSalesmanID());

            merchantModel = UtilsConstant.mapToBean(params , MerchantModel.class);

        }catch (Exception e){

            log.error("转换实体bean异常："  , e);

            return ;
        }

        log.info("业务员:" + user.getLoginID() + "修改结算卡信息卡号：" + merchantModel.getBankCardNo());


        boolean flag = AuthUtil.authen(  salesman.getName() , salesman.getIDNumber() ,  merchantModel.getBankCardNo());
        if(flag){
            response.setRespCode(RespCode.BankCardInfoErroe[0]);
            response.setRespDesc(RespCode.BankCardInfoErroe[1]);
            return ;
        }

//        Map<String,String> map =  userBankCardMapper.getBankName(paramter.getBankCardNo());
//        if(map == null || map.isEmpty()){
//            paramter.setRespCode(RespCode.BankCardInfoErroe[0]);
//            paramter.setRespDesc(RespCode.BankCardInfoErroe[1]);
//        }else{


            int x = salesManDB.updateBankNo(new Object[]{merchantModel.getBankCardNo() , salesman.getID()});

            if (x > 0) {

                log.info("业务员:" + user.getLoginID() + "修改结算信息成功");

//                response.setBankName(bankBinMap.get("bankName"));
//
//                String cardName = "储蓄卡";
//                if("CREDIT_CARD".equals(bankBinMap.get("cardName"))){
//                    cardName = "信用卡";
//                }
//                response.setCardName(cardName);
//                response.setBankSymbol(bankBinMap.get("bankCode"));

                response.setRespCode(RespCode.SUCCESS[0]);
                response.setRespDesc(RespCode.SUCCESS[1]);
            } else {

                log.info("业务员:" + user.getLoginID() + "修改结算信息保存数据库失败");

                response.setRespCode(RespCode.ServerDBError[0]);
                response.setRespDesc(RespCode.ServerDBError[1]);
            }
        }
//    }
}
