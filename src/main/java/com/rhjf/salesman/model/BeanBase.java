package com.rhjf.salesman.model;

/**
 * Created by hadoop on 2017/9/25.
 */
public class BeanBase {


    /**  业务员登录账号 **/
    private String loginID;


    /**  商户登录账号（标识） **/
    private String merchantLoginID;


    /**  请求类型 **/
    private String txndir;

    /**  终端流水号 **/
    private String sendSeqId;

    /** 发起交易时间 **/
    private String sendTime;

    /** 终端信息 **/
    private String terminalInfo;


    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public void setTxndir(String txndir) {
        this.txndir = txndir;
    }

    public String getTxndir() {
        return txndir;
    }

    public String getSendSeqId() {
        return sendSeqId;
    }

    public void setSendSeqId(String sendSeqId) {
        this.sendSeqId = sendSeqId;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
    }

    public String getTerminalInfo() {
        return terminalInfo;
    }

    public String getMerchantLoginID() {
        return merchantLoginID;
    }

    public void setMerchantLoginID(String merchantLoginID) {
        this.merchantLoginID = merchantLoginID;
    }
}
