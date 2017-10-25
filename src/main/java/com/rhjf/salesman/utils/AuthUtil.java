package com.rhjf.salesman.utils;

import com.rhjf.salesman.constant.Constants;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hadoop on 2017/9/12.
 */
@Component
public class AuthUtil {

    private static Logger log = LoggerFactory.getLogger(AuthUtil.class);

    public static String CHANNLENO;

    public static String CHANNELNAME;

    public static String DES3KEY;

    public static String SIGNKEY;

    public static String AUTHENCATION_URL;


    @Value("${REPORT_CHANNELNO}")
    public  void setChannelNo(String REPORT_CHANNELNO) {
        CHANNLENO = REPORT_CHANNELNO;
    }
    @Value("${REPORT_CHANNELNAME}")
    public void setChannelName(String REPORT_CHANNELNAME) {
        CHANNELNAME = REPORT_CHANNELNAME;
    }

    @Value("${REPORT_DES3_KEY}")
    public void setDes3Key(String REPORT_DES3_KEY) {
        DES3KEY = REPORT_DES3_KEY;
    }

    @Value("${REPORT_SIGN_KEY}")
    public void setSignKey(String REPORT_SIGN_KEY) {
        SIGNKEY = REPORT_SIGN_KEY;
    }


    @Value("${AUTHENCATION_URL}")
    public void setAuthencationUrl(String AUTHENCATION_URL){
        this.AUTHENCATION_URL = AUTHENCATION_URL;
    }



//    public  static  boolean authencation(AuthenticationMapper authenticationMapper , String name , String IDCardNo , String bankCardNo){
//        Map<String, String> bankAuthencationMan = authenticationMapper.bankAuthenticationInfo(bankCardNo);
//        if (bankAuthencationMan == null || bankAuthencationMan.isEmpty()) {
//
//            log.info("未查到卡号：" + bankCardNo + "的鉴权信息");
//
//            Map<String, String> authMap = new HashMap<String, String>();
//            AuthService authService = new AuthService();
//            authMap.put("accName", name);
//            authMap.put("cardNo", bankCardNo);
//            authMap.put("certificateNo", IDCardNo);
//            Map<String, String> reqMap = authService.authKuai(authMap);
//
//            log.info("新商户：鉴权，" + authMap.toString() + "鉴权结果:" + reqMap.toString());
//            if (!reqMap.get("respCode").equals(Author.SUCESS_CODE)) {
//                log.info("业务员新增用户： 银行信息鉴权没有通过");
//
//                return true;
//            } else {
//
//                //  鉴权通过 将银行卡鉴权信息保存数据库
//                Map<String, String> bankInfo = new HashMap<>();
//                bankInfo.put("ID", UtilsConstant.getUUID());
//                bankInfo.put("IdNumber", IDCardNo);
//                bankInfo.put("RealName", name);
//                bankInfo.put("BankCardNo", bankCardNo);
//                bankInfo.put("RespCode", "00");
//                bankInfo.put("RespDesc", reqMap.get("respMsg"));
//                log.info("鉴权通过。将" + bankCardNo +"保存数据库");
//
//                authenticationMapper.addAuthencationInfo(bankInfo);
//            }
//        } else {
//            if (!name.equals(bankAuthencationMan.get("RealName")) || !IDCardNo.equals(bankAuthencationMan.get("IdNumber"))) {
//                log.info("业务员新增用户：银行信息鉴权没有通过");
//                return true;
//            }else{
//                log.info("卡号：" + bankCardNo + "查询到历史鉴权数据,并且信息一致");
//            }
//        }
//        return false;
//    }


    public  static  boolean authen(String name , String IDCardNo , String bankCardNo){
        try {
            Map<String,Object> map = new TreeMap<String,Object>();
            map.put("channelNo", CHANNLENO);
            map.put("channelName", CHANNELNAME);
            map.put("orderNo", UtilsConstant.RandCode());
            map.put("cardNo", DESUtil.encode(DES3KEY,bankCardNo));
            map.put("name", DESUtil.encode(DES3KEY,name));
            map.put("idNo", DESUtil.encode(DES3KEY,IDCardNo));

            String sign = MD5.sign(JSONObject.fromObject(map) + SIGNKEY, "utf-8").toUpperCase();
            map.put("sign", sign);

            log.info("鉴权请求地址:" + AUTHENCATION_URL);
            log.info("鉴权请求报文：" + map.toString());

            String content = HttpClient.post(AUTHENCATION_URL , map , null);

            log.info("鉴权响应报文：" + content);

            JSONObject json = JSONObject.fromObject(content);

            log.info("新商户：鉴权，" + map.toString() + "鉴权结果:" + json.toString());
            if (!json.getString("resCode").equals(Constants.payRetCode)) {
                log.info("业务员新增用户： 银行信息鉴权没有通过");
                return true;
            } else {
                log.info("鉴权通过。银行卡号" + bankCardNo );
                return false;
            }
        }catch (Exception e){
            log.error("鉴权接口发生异常：" + e.getMessage()  , e);
            return true;
        }
    }

}
