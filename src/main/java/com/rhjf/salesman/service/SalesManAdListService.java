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

        array.add("http://app.ronghuijinfubj.com/web/public/2.png");
        array.add("http://app.ronghuijinfubj.com/web/public/3.png");


        json.put("imgurl" , array);


        array = new JSONArray();

        array.add("");
        array.add("");


        json.put("adurl" ,array );


        array = new JSONArray();

        array.add("");
        array.add("");


        json.put("title" ,array );


        response.setList(json.toString());

        response.setRespCode(RespCode.SUCCESS[0]);
        response.setRespDesc(RespCode.SUCCESS[1]);


    }
}
