package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.BinverifyDB;
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
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hadoop on 2017/9/29.
 *
 * @author hadoop
 */
@Service("SalesManUpdateBankCardNoService")
public class SalesManUpdateBankCardNoService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesManDB salesManDB;


    @Autowired
    private BinverifyDB binverifyDB;

    public void SalesManUpdateBankCardNO(SalesmanLogin user, Map params, ResponseData response) {


        SalesMan salesman;
        MerchantModel merchantModel;
        try {
            salesman = salesManDB.getSalesMan(user.getSalesmanID());

            merchantModel = UtilsConstant.mapToBean(params, MerchantModel.class);

        } catch (Exception e) {
            log.error("转换实体bean异常：", e);
            return;
        }

        log.info("业务员:" + user.getLoginID() + "修改结算卡信息卡号：" + merchantModel.getBankCardNo());

        boolean flag = AuthUtil.authentication(salesman.getName(), salesman.getIDNumber(), merchantModel.getBankCardNo() , salesman.getPhone());
        if (flag) {
            response.setRespCode(RespCode.BankCardInfoErroe[0]);
            response.setRespDesc(RespCode.BankCardInfoErroe[1]);
            return;
        }

        salesman.setID(salesman.getID());
        Map<String, Object> bankBinMap = binverifyDB.bankBin(merchantModel.getBankCardNo());
        salesman.setBankName(UtilsConstant.ObjToStr(bankBinMap.get("BANKNAME")));

        int x = salesManDB.updateBankNo(new Object[]{merchantModel.getBankCardNo(), merchantModel.getPayerPhone(), salesman.getBankName()  ,salesman.getID()});


        if (x > 0) {

            log.info("业务员:" + user.getLoginID() + "修改结算信息成功");

            response.setBankName(UtilsConstant.ObjToStr(bankBinMap.get("BANKNAME")));

            String cardName = "储蓄卡";
            if ("CREDIT_CARD".equals(bankBinMap.get("CARDNAME"))) {
                cardName = "信用卡";
            }
            response.setCardName(cardName);
            response.setBankSymbol(UtilsConstant.ObjToStr(bankBinMap.get("BANKCODE")));

            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);
        } else {

            log.info("业务员:" + user.getLoginID() + "修改结算信息保存数据库失败");

            response.setRespCode(RespCode.ServerDBError[0]);
            response.setRespDesc(RespCode.ServerDBError[1]);
        }
    }
}
