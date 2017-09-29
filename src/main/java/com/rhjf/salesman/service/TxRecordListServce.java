package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.db.WithdrawDB;
import com.rhjf.salesman.model.ResponseData;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 *
 *   提现记录
 * Created by hadoop on 2017/9/29.
 */
@Service("TxRecordListServce")
public class TxRecordListServce {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WithdrawDB withdrawDB;

    public void TxRecordList(SalesmanLogin user , Map params , ResponseData response){

        List<Map<String,Object>> list = withdrawDB.txlist(new Object[]{user.getID()});

        response.setList(JSONArray.fromObject(list).toString());

        response.setRespCode(RespCode.SUCCESS[0]);
        response.setRespDesc(RespCode.SUCCESS[1]);
    }
}
