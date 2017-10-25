package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.BinverifyDB;
import com.rhjf.salesman.db.SalesManDB;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesMan;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by hadoop on 2017/10/23.
 *
 * @author hadoop
 */
@Service("IndexService")
public class IndexService {


    @Autowired
    private SalesManDB salesManDB;


    @Autowired
    private BinverifyDB binverifyDB;


    public void index(SalesmanLogin user, Map params, ResponseData response) {


        response.setProfitTotal(user.getFeeAmount().toString());

        response.setFeeBalance(user.getFeeBalance().toString());


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        response.setRegisterDate(sdf.format(user.getCreateTime()));

        try {
            SalesMan salesMan = salesManDB.getSalesMan(user.getSalesmanID());

            Class clazz = salesMan.getClass();

            Field[] fields = clazz.getDeclaredFields();

            JSONObject json = new JSONObject();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(salesMan) != null) {
                    json.put(field.getName(), field.get(salesMan));
                }
            }

            Map<String,Object> binBankMap = binverifyDB.bankBin(salesMan.getBankNo());

            if(binBankMap!=null){
                json.put("bankName", UtilsConstant.ObjToStr(binBankMap.get("BANKNAME")));

                String cardName = "储蓄卡";
                if ("CREDIT_CARD".equals(binBankMap.get("cardName"))) {
                    cardName = "信用卡";
                }
                json.put("cardName", cardName);
                json.put("bankSybol", UtilsConstant.ObjToStr(binBankMap.get("BANKCODE")));
            }

            response.setList(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        response.setAboutURL("00");
        response.setCompanyAptitudeURL("00");

        response.setRespCode(RespCode.SUCCESS[0]);
        response.setRespDesc(RespCode.SUCCESS[1]);
    }
}
