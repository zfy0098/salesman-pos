package com.rhjf.salesman.model;


import java.io.Serializable;
/**
 * Created by hadoop on 2017/9/26.
 */
public class SalesmanKey implements Serializable{


    private Integer ID;

    private String macKey;

    private String salesmanID;

    private String tmkKey;

    private String termTmkKey;


    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setMacKey(String macKey) {
        this.macKey = macKey;
    }

    public String getMacKey() {
        return macKey;
    }

    public String getTmkKey() {
        return tmkKey;
    }

    public void setTmkKey(String tmkKey) {
        this.tmkKey = tmkKey;
    }

    public String getTermTmkKey() {
        return termTmkKey;
    }

    public void setTermTmkKey(String termTmkKey) {
        this.termTmkKey = termTmkKey;
    }


    public String getSalesmanID() {
        return salesmanID;
    }

    public void setSalesmanID(String salesmanID) {
        this.salesmanID = salesmanID;
    }
}
