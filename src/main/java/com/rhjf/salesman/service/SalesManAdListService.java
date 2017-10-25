package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.model.ResponseData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;


/**
 * Created by hadoop on 2017/10/23.
 *
 * @author hadoop
 */
@Service("salesManADListService")
@Transactional
public class SalesManAdListService {


    public void adList(Map params , ResponseData response){

        JSONObject json = new JSONObject();


        JSONArray array = new JSONArray();

        array.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3786284623,3948485114&fm=27&gp=0.jpg");
        array.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508734038164&di=57f85505977005ff13cd806ef063e9a1&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F203fb80e7bec54e7736f1e16b3389b504fc26a32.jpg");


        json.put("imgurl" , array);


        array = new JSONArray();

        array.add("http://www.baidu.com");
        array.add("http://www.alibaba.com");


        json.put("adurl" ,array );


        array = new JSONArray();

        array.add("百度");
        array.add("阿里");


        json.put("title" ,array );


        response.setList(json.toString());

        response.setRespCode(RespCode.SUCCESS[0]);
        response.setRespDesc(RespCode.SUCCESS[1]);


    }
}
