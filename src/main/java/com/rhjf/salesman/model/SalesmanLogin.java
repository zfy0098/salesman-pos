package com.rhjf.salesman.model;

import java.io.Serializable;

/**
 * Created by hadoop on 2017/9/26.
 */
public class SalesmanLogin implements Serializable{

    private String ID;
    private String loginID;
    private String password;
    private String createTime;
    private String feeAmount;
    private String feeBalance;
    private String salesmanID;
    private String loginPSN;
    private String lastLoginTime;


    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getFeeBalance() {
        return feeBalance;
    }

    public void setFeeBalance(String feeBalance) {
        this.feeBalance = feeBalance;
    }

    public void setSalesmanID(String salesmanID) {
        this.salesmanID = salesmanID;
    }

    public String getSalesmanID() {
        return salesmanID;
    }

    public void setLoginPSN(String loginPSN) {
        this.loginPSN = loginPSN;
    }

    public String getLoginPSN() {
        return loginPSN;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }
}
