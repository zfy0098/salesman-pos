package com.rhjf.salesman.service;

import com.rhjf.salesman.db.WithdrawDB;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.model.WithdrawModel;
import com.rhjf.salesman.utils.UtilsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by hadoop on 2017/9/29.
 */

@Service("WithdrawService")
@Transactional
public class WithdrawService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WithdrawDB withdrawDB;

    public void txProfit(SalesmanLogin user , Map params , ResponseBody response){


        try {

            WithdrawModel withdrawModel = UtilsConstant.mapToBean(params , WithdrawModel.class);


            Integer feeBalance = Integer.parseInt(user.getFeeBalance());
            Integer amount = Integer.parseInt(withdrawModel.getAmount());

            if(amount > feeBalance){
                log.info("余额不足： 当前余额：" + feeBalance + " , 提现余额 ：" + amount );
                return ;
            }

            withdrawDB.tx(user.getID() , withdrawModel.getAmount() , withdrawModel.getSendSeqId());
        }catch (Exception e){

        }

    }

}
