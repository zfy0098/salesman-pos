package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.SalesmanKeyDB;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanKey;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.utils.DESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用户签到
 * Created by hadoop on 2017/9/26.
 */
@Service("signService")
@Transactional
public class SignService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SalesmanKeyDB salesmanKeyDB;

    @Value("${initKey}")
    private String initKey;

    @Value("${DBINITKEY}")
    private String DBINITKEY;

    public void sign(SalesmanLogin user, Map params, ResponseData response) {
        log.info("用户" + user.getLoginID() + "执行签到操作");


        SalesmanKey termKey = salesmanKeyDB.salesmanKeyInfo(user.getID());
        Map<String, String> map = GetKey(termKey.getTermTmkKey());

        int x = salesmanKeyDB.updateMacKey(new Object[]{map.get("keyDB").toString(), user.getID()});

        response.setSecretKey(map.get("keyTerm").toString());

        if (x > 0) {

            log.info("用户：" + user.getLoginID() + "签到成功 , 获得秘钥:" + map.get("keyTerm").toString());
            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);
        } else {
            log.info("用户：" + user.getLoginID() + "签到保存数据失败");
            response.setRespCode(RespCode.ServerDBError[0]);
            response.setRespDesc(RespCode.ServerDBError[1]);
        }

    }


    public HashMap<String, String> GetKey(String tmkEncry) {

        Random random = new Random();
        HashMap<String, String> keyMap = new HashMap<String, String>();
        char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            StringBuffer ret = new StringBuffer();
            for (int i = 0; i < 32; i++) {
                ret.append(String.valueOf(codeSequence[random.nextInt(16)]));
            }

            String data = ret.toString();
            // 解密TMK
            String tmk = DESUtil.bcd2Str(DESUtil.decrypt3(tmkEncry, initKey));

            //  获取校验码
            String checkCode = DESUtil.bcd2Str(DESUtil.encrypt3("0000000000000000", data));

            // 生成下发给终端的密钥
            String keyTerm = DESUtil.bcd2Str(DESUtil.encrypt3(data, tmk));
            // 生成存放到数据的密钥
            String keyDB = DESUtil.bcd2Str(DESUtil.encrypt3(data, DBINITKEY));
            keyMap.put("keyTerm", keyTerm);
            keyMap.put("keyDB", keyDB);
            keyMap.put("checkCode", checkCode.substring(0, 8));

        } catch (Exception e) {
            return null;
        }
        return keyMap;
    }

}
