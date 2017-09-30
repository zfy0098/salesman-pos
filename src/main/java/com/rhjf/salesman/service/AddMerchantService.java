package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.Constants;
import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.BankCodeDB;
import com.rhjf.salesman.db.InternetMerchantDB;
import com.rhjf.salesman.db.InternetMerchantFeeDB;
import com.rhjf.salesman.db.InternetMerchantKeyDB;
import com.rhjf.salesman.model.MerchantModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

/**
 *
 *     添加商户
 *
 * Created by hadoop on 2017/9/26.
 */
@Service("addMerchantService")
@Transactional
public class AddMerchantService {


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private InternetMerchantKeyDB internetMerchantKeyDB;

    @Autowired
    private InternetMerchantDB internetMerchantDB;

    @Autowired
    private InternetMerchantFeeDB internetMerchantFeeDB;

    @Autowired
    private BankCodeDB bankCodeDB;

    public void inputMerchant(SalesmanLogin user , Map params , ResponseData response){
        try{
            /** 随机生成的营业执照号 **/
            String businessLicense = UtilsConstant.RandCode();

            Random random = new Random(Constants.alipayMCCType.length - 1);
            int index = random.nextInt(Constants.alipayMCCType.length - 1);
            String alipaymcccNumber = Constants.alipayMCCType[index];

            random = new Random(Constants.wxMCCType.length - 1);
            index = random.nextInt(Constants.wxMCCType.length - 1);
            Integer wxmcccNumber = Constants.wxMCCType[index];

            String bankType = "TOPRIVATE";

            MerchantModel merchantInfo = UtilsConstant.mapToBean(params , MerchantModel.class);


            Map<String,Object> bankCodeMap = bankCodeDB.getBankCodeInfo(merchantInfo.getBankSubbranch());


            log.info("保存新增商户：" + merchantInfo.getMerchantLoginID() + "基本信息");
            int merchantID = internetMerchantDB.saveInternetMerchantInfo(new Object[]{user.getID() , merchantInfo.getName() , merchantInfo.getMerchantName() , merchantInfo.getName()
                    , merchantInfo.getIDCardNo() , merchantInfo.getState() , merchantInfo.getCity() , merchantInfo.getCounty() , merchantInfo.getAddress() ,
                    businessLicense , merchantInfo.getBankCardNo() , bankCodeMap.get("BANKNAME") , bankCodeMap.get("BANKCODE") , merchantInfo.getBankSubbranch(),
                    merchantInfo.getD0falg() , merchantInfo.getMccCategory()});

                            /**  保存商户费率 **/
            internetMerchantFeeDB.saveMerchantFee(new Object[]{merchantID , merchantInfo.getWxt1FeeRate() , merchantInfo.getWxT0FeeRate() ,
                    merchantInfo.getAlit0FeeRate() , merchantInfo.getAlit1FeeRate() , merchantInfo.getDebitCardRate() , merchantInfo.getDebitCardTopLimit() ,
                    merchantInfo.getCreditCardRate() });


            response.setRespDesc(RespCode.SUCCESS[1]);
            response.setRespCode(RespCode.SUCCESS[0]);


//            Map<String, Object> merchantInMap = new TreeMap<String, Object>();
//            merchantInMap.put("channelName", Constants.REPORT_CHANNELNAME);
//            merchantInMap.put("channelNo", Constants.REPORT_CHANNELNO);
//            merchantInMap.put("merchantName", merchantInfo.getMerchantName());
//            merchantInMap.put("merchantBillName", merchantInfo.getMerchantBillName());
//            merchantInMap.put("installProvince", merchantInfo.getState());
//            merchantInMap.put("installCity", merchantInfo.getCity());
//            merchantInMap.put("installCounty", merchantInfo.getCounty());
//            merchantInMap.put("operateAddress", merchantInfo.getAddress());
//            merchantInMap.put("merchantType", "PERSON");
//            merchantInMap.put("businessLicense", businessLicense);
//            merchantInMap.put("legalPersonName", merchantInfo.getName());
//            merchantInMap.put("legalPersonID", merchantInfo.getIDCardNo());
//            merchantInMap.put("merchantPersonName", merchantInfo.getName());
//            merchantInMap.put("merchantPersonPhone", merchantInfo.getMerchantLoginID());
//
//            merchantInMap.put("wxType", wxmcccNumber);
//            merchantInMap.put("wxT1Fee", merchantInfo.getWxt1FeeRate());
//            merchantInMap.put("wxT0Fee", merchantInfo.getWxT0FeeRate());
//
//            merchantInMap.put("alipayType", alipaymcccNumber);
//            merchantInMap.put("alipayT1Fee", merchantInfo.getAlit1FeeRate());
//            merchantInMap.put("alipayT0Fee", merchantInfo.getAlit0FeeRate());
//
//
//            merchantInMap.put("bankType", bankType);
//            merchantInMap.put("accountName", merchantInfo.getName());
//            merchantInMap.put("accountNo", DESUtil.encode(Constants.REPORT_DES3_KEY, merchantInfo.getBankCardNo()));
//            merchantInMap.put("bankName", bankCodeMap.get("BANKNAME"));
//            merchantInMap.put("bankProv", merchantInfo.getBankProv());
//            merchantInMap.put("bankCity", merchantInfo.getBankCity());
//            merchantInMap.put("bankBranch", merchantInfo.getBankSubbranch());
//            merchantInMap.put("bankCode", bankCodeMap.get("BANKCODE"));
//
//
//            log.info("需要签名的的数据：" + JSONObject.fromObject(merchantInMap).toString() + Constants.REPORT_SIGN_KEY);
//
//            String sign = MD5.sign(JSONObject.fromObject(merchantInMap).toString() + Constants.REPORT_SIGN_KEY, "utf-8");
//            merchantInMap.put("sign", sign.toUpperCase());
//
//            log.info("用户" + merchantInfo.getLoginID() + "入网请求报文:" + merchantInMap.toString());

//            JSONObject respJS = null;
//            String respCode = null;
//            try {
//                String content = HttpClient.post(Constants.REPORT_URL, merchantInMap, null);
//                log.info("用户" + merchantInfo.getLoginID() + "入网响应报文:" + content);
//
//                respJS = JSONObject.fromObject(content);
//                respCode = respJS.getString("respCode");
//            } catch (Exception e) {
//
//                log.info(user.getLoginID() + "入网异常：" + e.getMessage());
//                log.error("新增商户" + merchantInfo.getLoginID() + "入网异常:", e);
//
//                response.setRespCode("01");
//                response.setRespDesc("失败,请稍后再试");
//            }
//
//
//            //  入网返回具体描述信息
//            String respMsg = "";
//
//            if (Constants.payRetCode.equals(respCode)) {
//
//                log.info("商户：" + merchantInfo.getMerchantLoginID() + "在平台入网成功,保存商户秘钥等信息");
//
//                String merchantNo = respJS.getString("merchantNo");// 商户号
//                String signKey = respJS.getString("signKey");        //  微信签名秘钥
//                String desKey = respJS.getString("desKey");            //  微信des秘钥
//                String queryKey = respJS.getString("queryKey");        //  查询秘钥
//
//                String AlipaySignKey = respJS.getString("AlipaySignKey");    // 支付宝签名秘钥
//                String AlipaydesKey = respJS.getString("AlipaydesKey");        // 支付des秘钥
//
//                log.info("保存新增商户：" + merchantInfo.getMerchantLoginID() + "基本信息");
//                int merchantID = internetMerchantDB.saveInternetMerchantInfo(new Object[]{user.getID() , merchantInfo.getName() , merchantInfo.getMerchantName() , merchantInfo.getName()
//                        , merchantInfo.getIDCardNo() , merchantInfo.getState() , merchantInfo.getCity() , merchantInfo.getCounty() , merchantInfo.getAddress() ,
//                        businessLicense , merchantInfo.getBankCardNo() , bankCodeMap.get("BANKNAME") , bankCodeMap.get("BANKCODE") , merchantInfo.getBankSubbranch()});
//
//
//                /**
//                 *  保存商户通道商编和密码信息
//                 */
//                List<Object[]> list = new ArrayList<Object[]>();
//                Object[] obj = new Object[]{merchantID , merchantNo , desKey , signKey ,queryKey ,Constants.PayChannelWXScancode};
//                list.add(obj);
//
//                obj = new Object[]{merchantID , merchantNo , AlipaydesKey , AlipaySignKey ,queryKey ,Constants.payChannelAliScancode};
//                list.add(obj);
//
//                log.info("保存互联网商户秘钥信息");
//                internetMerchantKeyDB.saveMerchantTradeKey(list);
//
//                /**  保存商户费率 **/
//                internetMerchantFeeDB.saveMerchantFee(new Object[]{merchantID , merchantInfo.getWxt1FeeRate() , merchantInfo.getWxT0FeeRate() ,
//                        merchantInfo.getAlit0FeeRate() , merchantInfo.getAlit1FeeRate() , merchantInfo.getDebitCardRate() , merchantInfo.getDebitCardTopLimit() ,
//                        merchantInfo.getCreditCardRate() });
//
//                response.setRespCode(RespCode.SUCCESS[0]);
//                response.setRespDesc(RespCode.SUCCESS[1]);
//
//            } else {
//                if (respJS.has("respMsg")) {
//                    respMsg = respJS.getString("respMsg");
//                }
//                log.info(user.getLoginID() + "入网异常：上游报备失败 , " + respMsg);
//
//                response.setRespCode("01");
//                response.setRespDesc(respMsg);
//            }

        }catch (Exception e){

            log.error("入网出现异常：" ,  e);

            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);
        }
    }
}
