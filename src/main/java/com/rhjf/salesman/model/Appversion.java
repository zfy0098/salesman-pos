package com.rhjf.salesman.model;

/**
 * Created by hadoop on 2017/10/25.
 *
 * @author hadoop
 */
public class Appversion {


    private Integer ID;
    private String versionNo;
    private Integer open;
    private String deviceType;

    private Integer tradeTypeOpen;



    public Appversion() {
    }

    public Appversion(Integer ID, String versionNo, Integer open, String deviceType, Integer tradeTypeOpen) {
        this.ID = ID;
        this.versionNo = versionNo;
        this.open = open;
        this.deviceType = deviceType;
        this.tradeTypeOpen = tradeTypeOpen;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }


    public Integer getTradeTypeOpen() {
        return tradeTypeOpen;
    }

    public void setTradeTypeOpen(Integer tradeTypeOpen) {
        this.tradeTypeOpen = tradeTypeOpen;
    }
}
