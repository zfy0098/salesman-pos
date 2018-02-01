package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.Constants;
import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.*;
import com.rhjf.salesman.model.MerchantModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesMan;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 *     添加商户
 *
 * Created by hadoop on 2017/9/26.
 *
 *
 * @author zfy
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

    @Autowired
    private OnlinePayMerchantDB  onlinePayMerchantDB;

    @Autowired
    private AgentDB agentDB;

    @Autowired
    private SalesManDB salesManDB;

    @Autowired
    private MerchantPhotoDB merchantPhotoDB;

    /**
     *   互联网代理商编号
     */
    @Value("${REPORT_CHANNELNO}")
    public  String REPORT_CHANNELNO;


    /**
     *  互联网代理商名称
     */
    @Value("${REPORT_CHANNELNAME}")
    public String REPORT_CHANNELNAME;


    /**
     *  互联网des秘钥
     */
    @Value("${REPORT_DES3_KEY}")
    public String DES3KEY;

    /**
     *   互联网签名秘钥
     */
    @Value("${REPORT_SIGN_KEY}")
    public String REPORT_SIGN_KEY;


    /**
     *   鉴权地址
     */
    @Value("${AUTHENCATION_URL}")
    public String AUTHENCATION_URL;


    /**
     *  互联网入网地址
     */
    @Value("${REPORT_URL}")
    public String REPORT_URL;


    /**
     *  pos 入网地址
     */
    @Value("${POS_MERCHANT_REPORT_URL}")
    public String POS_MERCHANT_REPORT_URL;


    @Value("${imgPath}")
    private String imgPath;

    @Value("${imgUrl}")
    private String imgUrl;


    public void inputMerchant(SalesmanLogin user , Map params , ResponseData response){
        try{

            log.info(" 业务员：" + user.getLoginID() + "开始添加商户");

            Random random = new Random(Constants.alipayMCCType.length - 1);
            int index = random.nextInt(Constants.alipayMCCType.length - 1);
            String aliPayMccNumber = Constants.alipayMCCType[index];

            random = new Random(Constants.wxMCCType.length - 1);
            index = random.nextInt(Constants.wxMCCType.length - 1);
            Integer wxMccNumber = Constants.wxMCCType[index];

            MerchantModel merchantInfo = UtilsConstant.mapToBean(params , MerchantModel.class);

            Map<String,Object> bankCodeMap = bankCodeDB.getBankCodeInfo(merchantInfo.getBankSubbranch());

            String key = merchantInfo.getMcc();

            SalesMan salesMan = salesManDB.getSalesMan(user.getSalesmanID());

            Map<String,Object> agentInfo = agentDB.agentInfo(salesMan.getAgentID());
            String posAgentNo = UtilsConstant.ObjToStr(agentInfo.get("AGENT_NO"));

            boolean flag = AuthUtil.authentication(merchantInfo.getAccountName() ,merchantInfo.getIDCardNo() , merchantInfo.getBankCardNo() , merchantInfo.getPayerPhone());
            if(flag){
                log.info("业务员：" + user.getLoginID() + "新增的商户： " + merchantInfo.getMerchantPhone() + " 鉴权信息没有通过");
                response.setRespCode(RespCode.BankCardInfoErroe[0]);
                response.setRespDesc(RespCode.BankCardInfoErroe[1]);
                return ;
            }

            Map<String, Object> posMap = new HashMap<String, Object>(16);
            //原平台商户号 原值返回
            posMap.put("srcMerNo", merchantInfo.getMerchantPhone());
            //代理商标号
            posMap.put("agentNo", posAgentNo);
            //经营范围
            posMap.put("bizDomain", merchantInfo.getBusinessScope());
            //mcc
            posMap.put("mcc", key);
            //商户名全称
            posMap.put("fullName", merchantInfo.getMerchantName());
            //商户简称
            posMap.put("shortName", merchantInfo.getMerchantName());
            //注册地址
            posMap.put("address", merchantInfo.getAddress());
            //安装归属省
            posMap.put("province", merchantInfo.getState());
            //安装归属市
            posMap.put("city", merchantInfo.getCity());
            //安装归属地址
            posMap.put("posShopAddress", merchantInfo.getAddress());
            //联系人
            posMap.put("posPerson", merchantInfo.getName());
            //电话
            posMap.put("posContact", merchantInfo.getMerchantPhone());
            //法人
            posMap.put("legalName", merchantInfo.getName());

            posMap.put("legalContact", merchantInfo.getMerchantPhone());
            //法人证件号码
            posMap.put("legalId", merchantInfo.getIDCardNo());
            //营业执照号
            posMap.put("licenseNo", merchantInfo.getBusinessLicense());
            //银行编码
            posMap.put("settleBank", bankCodeMap.get("BANKSYMBOLS"));
            //入账对公/对私  目前只支持对私  对公-ENTERPRISE  对私--PERSONAL
            posMap.put("settleAccountType", "1".equals(merchantInfo.getSettleAccountType())?"PERSONAL" : "ENTERPRISE");
            //开户行名称
            posMap.put("settleSubbranch", merchantInfo.getBankSubbranch());
            //开户账号
            posMap.put("settleAccountNo", merchantInfo.getBankCardNo());
            //入账人名称
            posMap.put("settleAccountName", merchantInfo.getAccountName());
            //支付系统行号
            posMap.put("settleUnionNo", bankCodeMap.get("BANKCODES"));
            //结算卡关联手机号
            posMap.put("settleMobile",  merchantInfo.getPayerPhone());
            //借记卡费率
            posMap.put("rate", merchantInfo.getDebitCardRate());
            //借记卡封顶金额-元/笔
            posMap.put("ceilingLimit", merchantInfo.getDebitCardTopLimit());
            //贷记卡费率-?%
            posMap.put("ccRate", merchantInfo.getCreditCardRate());
            //D0 标志  Y/N
            posMap.put("d0Flag", merchantInfo.getD0falg());
            //商户类型 标准类1  优惠类2
            posMap.put("mccCategory", merchantInfo.getMccCategory());
            posMap.put("settleProvince", merchantInfo.getBankProv());
            posMap.put("settleCity", merchantInfo.getBankCity());
            posMap.put("salesmanNo", user.getSalesmanID());


            log.info("pos 入网请求报文：" + posMap.toString());

            String result = HttpClient.post(POS_MERCHANT_REPORT_URL, posMap, null);

            log.info("pos入网响应报文：" + result);

            JSONObject json = JSONObject.fromObject(result);

            String customer_no;

            String respCode = json.getString("respCode");

            if (Constants.payRetCode.equals(respCode)){
                log.info("pos 入网成功");
                customer_no = json.getString("CustomerNo");
            } else {
                log.info("pos 入网失败");
                response.setRespCode(respCode);
                response.setRespDesc(json.getString("respMsg"));
                return ;
            }

            Map<String, Object> merchantInMap = new TreeMap<String, Object>();
            merchantInMap.put("channelName", REPORT_CHANNELNAME);
            merchantInMap.put("channelNo", REPORT_CHANNELNO);
            merchantInMap.put("merchantName", merchantInfo.getMerchantName());
            merchantInMap.put("merchantBillName", merchantInfo.getName());
            merchantInMap.put("installProvince", merchantInfo.getState());
            merchantInMap.put("installCity", merchantInfo.getCity());
            merchantInMap.put("installCounty", merchantInfo.getCounty());
            merchantInMap.put("operateAddress", merchantInfo.getAddress());
            merchantInMap.put("merchantType", "PERSON");
            merchantInMap.put("businessLicense",  merchantInfo.getBusinessLicense());
            merchantInMap.put("legalPersonName", merchantInfo.getName());
            merchantInMap.put("legalPersonID", merchantInfo.getIDCardNo());
            merchantInMap.put("merchantPersonName", merchantInfo.getName());
            merchantInMap.put("merchantPersonPhone", merchantInfo.getMerchantPhone());

            merchantInMap.put("wxType", wxMccNumber);
            merchantInMap.put("wxT1Fee", merchantInfo.getWxT0FeeRate());
            merchantInMap.put("wxT0Fee", merchantInfo.getWxT0FeeRate());

            merchantInMap.put("alipayType", aliPayMccNumber);
            merchantInMap.put("alipayT1Fee", merchantInfo.getAlit0FeeRate());
            merchantInMap.put("alipayT0Fee", merchantInfo.getAlit0FeeRate());

            String bankType = "1".equals(merchantInfo.getSettleAccountType())?"TOPRIVATE":"TOPUBLIC";

            merchantInMap.put("bankType", bankType);
            merchantInMap.put("accountName", merchantInfo.getName());
            merchantInMap.put("accountNo", DESUtil.encode(DES3KEY , merchantInfo.getBankCardNo()));
            merchantInMap.put("bankName", bankCodeMap.get("BANKNAMES"));
            merchantInMap.put("bankProv", merchantInfo.getBankProv());
            merchantInMap.put("bankCity", merchantInfo.getBankCity());
            merchantInMap.put("bankBranch", merchantInfo.getBankSubbranch());
            merchantInMap.put("bankCode", bankCodeMap.get("BANKCODES"));

            if(!UtilsConstant.strIsEmpty(merchantInfo.getCreditCard())){
                merchantInMap.put("creditCardNo",DESUtil.encode(DES3KEY , merchantInfo.getCreditCard()));
            }

            log.info("需要签名的的数据：" + JSONObject.fromObject(merchantInMap).toString() + REPORT_SIGN_KEY);

            String sign = MD5.sign(JSONObject.fromObject(merchantInMap).toString() + REPORT_SIGN_KEY, "utf-8");
            merchantInMap.put("sign", sign.toUpperCase());

            log.info("用户" + merchantInfo.getLoginID() + "互联网 入网请求报文:" + merchantInMap.toString());

            JSONObject respJS = null;
            respCode = null;
            try {
                String content = HttpClient.post(REPORT_URL, merchantInMap, null);
                log.info("用户" + merchantInfo.getLoginID() + "入网响应报文:" + content);

                respJS = JSONObject.fromObject(content);
                respCode = respJS.getString("respCode");
            } catch (Exception e) {

                log.info(user.getLoginID() + "入网异常：" + e.getMessage());
                log.error("新增商户" + merchantInfo.getLoginID() + "入网异常:", e);

                response.setRespCode("01");
                response.setRespDesc("失败,请稍后再试");
            }

            Integer onlineStatus = 0;

            //  入网返回具体描述信息
            String respMsg = "";

            if (Constants.payRetCode.equals(respCode)) {

                log.info("商户：" + merchantInfo.getMerchantPhone() + "在平台入网成功,保存商户秘钥等信息");

                String merchantNo = respJS.getString("merchantNo");// 商户号
                String signKey = respJS.getString("signKey");        //  微信签名秘钥
                String desKey = respJS.getString("desKey");            //  微信des秘钥
                String queryKey = respJS.getString("queryKey");        //  查询秘钥

                String aliPaySignKey = respJS.getString("AlipaySignKey");    // 支付宝签名秘钥
                String aliPayDesKey = respJS.getString("AlipaydesKey");        // 支付des秘钥

                log.info("保存新增商户：" + merchantInfo.getMerchantPhone() + "基本信息");
                log.info("保存新增商户：" + merchantInfo.getMerchantPhone() + "基本信息");


                /**
                 *  保存商户通道商编和秘钥信息
                 */

                log.info("保存互联网商户秘钥信息");
                onlinePayMerchantDB.saveMerchantTradeKey(new Object[]{customer_no , merchantNo ,merchantInfo.getMerchantName()  , signKey  , desKey , queryKey ,
                        String.valueOf(AmountUtil.div( merchantInfo.getWxT0FeeRate()  , "100" ,3 )) , String.valueOf( AmountUtil.div( merchantInfo.getWxT0FeeRate()  , "100" ,3 ))
                        ,  String.valueOf( AmountUtil.div(merchantInfo.getAlit0FeeRate() , "100" ,3 )) ,
                        String.valueOf(AmountUtil.div( merchantInfo.getAlit0FeeRate() , "100" ,3 ))});

                onlineStatus = 1;

                response.setRespCode(RespCode.SUCCESS[0]);
                response.setRespDesc(RespCode.SUCCESS[1]);

            } else {


                log.info("customer_no:" + customer_no);
                log.info("merchantInfo.getMerchantName():" + merchantInfo.getMerchantName());
                log.info("merchantInfo.getWxT0FeeRate() :" + merchantInfo.getWxT0FeeRate() );
                log.info(" merchantInfo.getAlit0FeeRate():" +  merchantInfo.getAlit0FeeRate());

                onlinePayMerchantDB.initOnlineMerchantKey(new Object[]{ customer_no , merchantInfo.getMerchantName() ,
                        String.valueOf(AmountUtil.div( merchantInfo.getWxT0FeeRate()  , "100" ,3 )) , String.valueOf(AmountUtil.div( merchantInfo.getWxT0FeeRate()  , "100" ,3 ))
                        ,  String.valueOf(AmountUtil.div(merchantInfo.getAlit0FeeRate() , "100",3)) , String.valueOf(AmountUtil.div( merchantInfo.getAlit0FeeRate() , "100",3 ))});

                log.info("保存新增商户：" + merchantInfo.getMerchantPhone() + "基本信息");

                if (respJS.has("respMsg")) {
                    respMsg = respJS.getString("respMsg");
                }
                log.info(user.getLoginID() + "线上平台入网失败 , " + respMsg);
                response.setRespCode(RespCode.SUCCESS[0]);
                response.setRespDesc(respMsg);
            }

            int merchantID = internetMerchantDB.saveInternetMerchantInfo(new Object[]{user.getID() , merchantInfo.getName() , merchantInfo.getMerchantName() ,
                    merchantInfo.getName() , merchantInfo.getIDCardNo() , merchantInfo.getState() , merchantInfo.getCity() , merchantInfo.getCounty() ,
                    merchantInfo.getAddress() ,merchantInfo.getBusinessLicense() , merchantInfo.getBankCardNo() , bankCodeMap.get("BANKNAMES") ,
                    bankCodeMap.get("BANKCODES") , merchantInfo.getBankSubbranch(), merchantInfo.getD0falg() , merchantInfo.getMccCategory() ,
                    customer_no , 1 ,onlineStatus , merchantInfo.getBusinessScope() , merchantInfo.getBusinessRegistName(), UtilsConstant.ObjToStr(merchantInfo.getOrganizationCode()) ,
                    UtilsConstant.ObjToStr(merchantInfo.getCertificateRegist()) ,merchantInfo.getAccountName() , UtilsConstant.ObjToStr(merchantInfo.getCreditCard())});

            response.setCustomerNo(customer_no);

        }catch (Exception e){
            log.error("入网出现异常：" ,  e);
            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);
        }
    }
}
