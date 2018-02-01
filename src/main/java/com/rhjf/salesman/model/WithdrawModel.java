package com.rhjf.salesman.model;

/**
 * Created by hadoop on 2017/9/29.
 */
public class WithdrawModel extends  BeanBase{


    private String amount;



    private String tradeDate;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
}
