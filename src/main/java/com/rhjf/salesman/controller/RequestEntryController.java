package com.rhjf.salesman.controller;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanKey;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.service.SalesmanInfoService;
import com.rhjf.salesman.utils.DESUtil;
import com.rhjf.salesman.utils.PropertyUtils;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端接口访问入口
 *
 * @author hadoop
 */


@RestController
@RequestMapping("/request")
public class RequestEntryController {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${DBINITKEY}")
    private String dbInitKey;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object RequestEntry(@RequestParam(value = "data", required = true) String data, HttpServletRequest request) {


        if (UtilsConstant.strIsEmpty(data)) {
            log.info("终端请求报文为空,停止交易");
            JSONObject json = new JSONObject();
            json.put("respCode", RespCode.ParamsError[0]);
            json.put("respDesc", RespCode.ParamsError[1]);
            return paraFilterReturn(json);
        }
        log.info("请求报文：" + data.replace("\n", "").replace(" ", ""));

        ResponseData response = new ResponseData();

        try {

            JSONObject json = JSONObject.fromObject(data);
            Map<String, Object> map = UtilsConstant.jsonToMap(json);

            /** 发送时间  **/
            String sendTime = json.getString("sendTime");
            if (UtilsConstant.strIsEmpty(sendTime)) {
                log.info("发送时间sendTime为空");
                response.setRespCode(RespCode.ParamsError[0]);
                response.setRespDesc(RespCode.ParamsError[1]);
                return paraFilterReturn(response);
            }
            response.setSendTime(sendTime);

            /** 请求交易类型 **/
            String txndir = json.getString("txndir");
            if (UtilsConstant.strIsEmpty(txndir)) {
                log.info("交易类型txndir为空");
                response.setRespCode(RespCode.TxndirError[0]);
                response.setRespDesc(RespCode.TxndirError[1]);
                return response;
            }
            response.setTxndir(txndir);


            String trade = PropertyUtils.getValue(txndir);
            if (UtilsConstant.strIsEmpty(trade)) {
                log.info("交易类型：" + txndir + ", 系统未配置该交易类型");
                response.setRespCode(RespCode.TxndirError[0]);
                response.setRespDesc(RespCode.TxndirError[1]);
                return paraFilterReturn(response);
            }

            /** 终端流水号 **/
            String sendSeqID = json.getString("sendSeqId");
            if (UtilsConstant.strIsEmpty(sendSeqID)) {
                log.info("终端流水号sendSeqId为空");
                response.setRespCode(RespCode.ParamsError[0]);
                response.setRespDesc(RespCode.ParamsError[1]);
                return paraFilterReturn(response);
            }
            response.setSendSeqID(sendSeqID);


            /**  终端登录信息 **/
            String loginPSN = json.getString("terminalInfo");
            if (UtilsConstant.strIsEmpty(loginPSN)) {
                log.info("登录信息(PSN)terminalInfo为空");
                response.setRespCode(RespCode.ParamsError[0]);
                response.setRespDesc(RespCode.ParamsError[1]);
                return paraFilterReturn(response);
            }
            response.setTerminalInfo(loginPSN);


            log.info("请求class信息:" + trade);

            /** 执行beanid **/
            String className = trade.split(",")[0];
            /** 执行的方法名 **/
            String methodName = trade.split(",")[1];
            /** 是否需要登录信息 **/
            String needLogin = trade.split(",")[2];
            /** 是否需要mac **/
            String needMac = trade.split(",")[3];


            SalesmanLogin user = null;

            String mackey = null;

            /** 是否需要登录信息  **/
            if ("1".equals(needLogin)) {
                /** 获取登录信息  **/

                SalesmanInfoService salesmanInfoService = applicationContext.getBean("SalesmanInfoService", SalesmanInfoService.class);

                String loginID = json.getString("loginID");
                user = salesmanInfoService.SalesmanInfo(loginID);

                if (user == null) {
                    log.info("获取用户：" + loginID + "失败，系统不存在该账户");
                    response.setRespCode(RespCode.userDoesNotExist[0]);
                    response.setRespDesc(RespCode.userDoesNotExist[1]);
                } else {

                    if (!"A001".equals(txndir) && !"A004".equals(txndir)) {
                        if (!loginPSN.equals(user.getLoginPSN())) {
                            log.info(user.getLoginID() + "被其他设备登录 , 终端上传: " + loginPSN + ",数据库保存" + user.getLoginPSN());
                            response.setRespCode(RespCode.LOGINError[0]);
                            response.setRespDesc(RespCode.LOGINError[1]);
                            return paraFilterReturn(response);
                        }
                    }

                    boolean flag = true;
                    if ("1".equals(needMac)) {
                        /** 需要校验mac **/
                        //  获取用户秘钥信息
                        SalesmanKey termKey = salesmanInfoService.userTermkey(user.getID());
                        mackey = termKey.getMacKey();

                        String mac = makeMac(mackey, JSONObject.fromObject(data), user);

                        String clientMac = json.getString("mac");
                        if (!mac.equals(clientMac)) {
                            flag = false;
                            response.setRespCode(RespCode.SIGNMACError[0]);
                            response.setRespDesc(RespCode.SIGNMACError[1]);
                            log.info("验证mac失败，终端上送mac=[" + clientMac + "],平台计算mac=" + mac);
                        } else {
                            log.info("mac校验通过 == " + mac);
                        }
                    }
                    if (flag) {
                        Object obj = applicationContext.getBean(className);
                        Method m = obj.getClass().getMethod(methodName, new Class[]{SalesmanLogin.class, Map.class, ResponseData.class});

                        m.invoke(obj, user, map, response);
                    }
                }
            } else {
                /** 不需要登录信息 **/
                Object obj = applicationContext.getBean(className);
                Method m = obj.getClass().getMethod(methodName, new Class[]{Map.class, ResponseData.class});
                m.invoke(obj, map, response);
            }


            /** 如果请求需要校验mac 那么响应报文中也需要添加mac字段. 加密数据为返回的报文 **/
            if ("1".equals(needMac)) {
                String mac = makeMac(mackey, JSONObject.fromObject(response), user);
                log.info("响应报文中的mac" + mac);
                response.setMac(mac);
            }

        } catch (Exception e) {
            log.error("请求异常", e);
            response.setRespDesc(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);
        }

        Object obj = paraFilterReturn(response);
        log.info("响应报文：" + obj.toString());

        return obj;
    }


    /**
     * 去除参数提中 value 为null 的字段
     *
     * @param obj
     * @return
     */
    public Object paraFilterReturn(Object obj) {

        Map<String, Object> sArray = UtilsConstant.jsonToMap(JSONObject.fromObject(obj));
        Map<String, Object> sArray2 = new HashMap<String, Object>(16);
        if (sArray == null || sArray.size() <= 0) {
            return "";
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key) + "";
            if ((value == null || value.equals(""))) {
                continue;
            }
            sArray2.put(key + "", value);
        }
        return JSONObject.fromObject(sArray2);
    }


    /**
     * 计算mac
     *
     * @param json
     * @param user
     * @return
     */
    public String makeMac(String macKey, JSONObject json, SalesmanLogin user) {

        Map<String, Object> contentData = UtilsConstant.jsonToMap(json);
        String macStr = "";
        Object[] key_arr = contentData.keySet().toArray();
        Arrays.sort(key_arr);
        for (Object key : key_arr) {
            Object value = contentData.get(key);
            if (value != null && !UtilsConstant.strIsEmpty(value.toString())) {
                if (!key.equals("mac") && !key.equals("signImg")) {
                    macStr += value.toString();
                }
            }
        }
        log.info("加密原文：macStr:" + macStr);
        log.info("加密秘钥密文:" + macKey);
        String rMac = DESUtil.mac(macStr, macKey, dbInitKey);
        return rMac;
    }
}
