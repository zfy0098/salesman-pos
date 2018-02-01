package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.SalesManDB;
import com.rhjf.salesman.db.WithdrawDB;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesMan;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.model.WithdrawModel;
import com.rhjf.salesman.utils.DateJsonValueProcessor;
import com.rhjf.salesman.utils.DateUtil;
import com.rhjf.salesman.utils.UtilsConstant;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 2017/9/29.
 * @author  zfy
 */

@Service("WithdrawService")
@Transactional(rollbackFor = Exception.class)
public class WithdrawService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WithdrawDB withdrawDB;

    @Autowired
    private SalesManDB salesManDB;

    public void txProfit(SalesmanLogin user , Map params , ResponseData response){

        try {

            WithdrawModel withdrawModel = UtilsConstant.mapToBean(params , WithdrawModel.class);

            Double feeBalance = user.getFeeBalance();
            Double amount = Double.parseDouble(withdrawModel.getAmount());

            if(amount > feeBalance){
                log.info("余额不足： 当前余额：" + feeBalance + " , 提现余额 ：" + amount );
                return ;
            }

            SalesMan salesMan =  salesManDB.getSalesMan(user.getSalesmanID());

            Double withdrawAmount = Double.parseDouble(withdrawModel.getAmount()) - 1;

            withdrawDB.tx(user.getID() , withdrawAmount  , withdrawModel.getSendSeqId() , salesMan.getBankNo());


            response.setFeeBalance(new Double(user.getFeeBalance() - Double.parseDouble(withdrawModel.getAmount())).toString());
            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);
        }catch (Exception e){
            log.error("提现失败：" + e.getMessage() , e);
        }
    }


    public void TxRecordList(SalesmanLogin user , Map params , ResponseData response){

        try {

            WithdrawModel withdrawModel = UtilsConstant.mapToBean(params , WithdrawModel.class);


            StringBuffer tradeDate = new StringBuffer(withdrawModel.getTradeDate());

            if(tradeDate.length() < 6){
                tradeDate.insert(4 , '0');
            }

            List<Map<String,Object>> list = withdrawDB.txlist(new Object[]{user.getID() , tradeDate.toString()});


            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(DateUtil.yyyy_MM_ddHH_mm_ss));
            JSONArray array = JSONArray.fromObject(list,jsonConfig);


            Double totalAmount = withdrawDB.txTotalAmount(new Object[]{user.getID() , tradeDate.toString()});

            response.setProfitTotal(String.valueOf(totalAmount));

            response.setList(array.toString());

            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);


        }catch (Exception e){
            log.error("提现失败：" + e.getMessage() , e);
        }
    }
}
