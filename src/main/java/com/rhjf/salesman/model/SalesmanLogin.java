package com.rhjf.salesman.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hadoop on 2017/9/26.
 */
public class SalesmanLogin implements Serializable{

    private Integer ID;
    private String loginID;
    private String password;
    private Date createTime;
    private String feeAmount;
    private String feeBalance;
    private Integer salesmanID;
    private String loginPSN;
    private Date lastLoginTime;


    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
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


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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


    public Integer getSalesmanID() {return salesmanID;}

    public void setSalesmanID(Integer salesmanID) {
        this.salesmanID = salesmanID;
    }

    public void setLoginPSN(String loginPSN) {
        this.loginPSN = loginPSN;
    }

    public String getLoginPSN() {
        return loginPSN;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
