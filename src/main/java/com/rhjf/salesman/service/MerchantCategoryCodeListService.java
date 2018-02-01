package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.MCCConstant;
import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.model.MerchantModel;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hadoop on 2017/10/20.
 *
 * @author hadoop
 */
@Service("MerchantCategoryCodeListService")
public class MerchantCategoryCodeListService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void merchantCategoryCodeList(Map params, ResponseData response) {


        try {
            MerchantModel merchantModel = UtilsConstant.mapToBean(params, MerchantModel.class);

            Map<String, String> mcc = MCCConstant.biaoZhun;

            if ("2".equals(merchantModel.getMccCategory())) {
                mcc = MCCConstant.youHui;
            }

            JSONArray array = new JSONArray();

            for (String key : mcc.keySet()) {

                JSONObject json = new JSONObject();

                json.put("key", key);
                json.put("value", mcc.get(key));

                array.add(json);
            }

            response.setList(array.toString());
            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);

        } catch (Exception e) {
            log.error("获取mcc类别码异常，", e);
        }

    }
}
