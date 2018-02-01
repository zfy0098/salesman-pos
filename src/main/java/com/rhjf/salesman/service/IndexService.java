package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.AppversionDB;
import com.rhjf.salesman.db.BinverifyDB;
import com.rhjf.salesman.db.SalesManDB;
import com.rhjf.salesman.model.Appversion;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesMan;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesManDB salesManDB;


    @Autowired
    private BinverifyDB binverifyDB;


    @Autowired
    private AppversionDB appversionDB;



    @Value("${creditURL}")
    private String creditURL;

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


            Appversion appversion = appversionDB.appversionInfo(UtilsConstant.ObjToStr(params.get("deviceType")));

            response.setCreditURL(creditURL);
            response.setShopInfo(appversion.getTradeTypeOpen().toString());

            response.setOpen(appversion.getOpen().toString());

        } catch (Exception e) {
            log.error("获取初始化数据异常：" , e );
        }


        response.setAboutURL("http://app.ronghuijinfubj.com/web/public/salesman/about/about.html");
        response.setCompanyAptitudeURL("00");

        response.setRespCode(RespCode.SUCCESS[0]);
        response.setRespDesc(RespCode.SUCCESS[1]);
    }
}
