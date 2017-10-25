package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.BankCodeDB;
import com.rhjf.salesman.db.BinverifyDB;
import com.rhjf.salesman.model.MerchantModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 2017/9/28.
 */
@Service("BankBranchList")
public class BankBranchListService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BankCodeDB bankCodeDB;

    @Autowired
    private BinverifyDB binverifyDB;


    public void bankBranchList(Map params , ResponseData response){
        MerchantModel merchantModel;
        try {
            merchantModel = UtilsConstant.mapToBean(params , MerchantModel.class);
        }catch (Exception e){
            log.error("转换java bean 异常:" , e);
            return ;
        }

        String bankProv = merchantModel.getBankProv();
        String bankCity = merchantModel.getBankCity();
        String accountNo = merchantModel.getBankCardNo();

        Map<String,Object> binMap = binverifyDB.bankBin(merchantModel.getBankCardNo());

        if(binMap == null){
            response.setRespCode(RespCode.BankCardInfoErroe[0]);
            response.setRespDesc(RespCode.BankCardInfoErroe[1]);
            return ;
        }

        String bankName = UtilsConstant.ObjToStr(binMap.get("BANKNAME"));

        log.info("获取支行名称列表： 所在省份:" + bankProv + " , 城市:" + bankCity + ", 卡号：" + accountNo);


        log.info("获取支行名称：" +  bankName + "， 城市:" + bankCity + " , 省份:" + bankProv);

        List<String> list =  bankCodeDB.getBankBranchs(new Object[]{bankProv , bankCity , bankName});

        response.setList(JSONArray.fromObject(list).toString());

        response.setRespCode(RespCode.SUCCESS[0]);
        response.setRespDesc(RespCode.SUCCESS[1]);

    }
}
