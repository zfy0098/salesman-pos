package com.rhjf.salesman.model;

/**
 * Created by hadoop on 2017/10/23.
 *
 * @author hadoop
 */
public class SalesManProfitModel extends  BeanBase{


    private String staDate;

    private String endDate;


    public String getStaDate() {
        return staDate;
    }

    public void setStaDate(String staDate) {
        this.staDate = staDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
