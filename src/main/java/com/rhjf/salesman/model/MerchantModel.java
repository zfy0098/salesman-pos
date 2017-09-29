package com.rhjf.salesman.model;

/**
 * Created by hadoop on 2017/9/26.
 */
public class MerchantModel extends BeanBase{


    /**  姓名 **/
    private String name;

    /**  身份证号 **/
    private String IDCardNo;


    /**  商户名称 **/
    private String merchantName;


    /**  商户联系人 **/
    private String merchantBillName;

    /** 商户所在省份 **/
    private String state;

    /** 商户所在城市  **/
    private String city;

    /** 商户所在区 **/
    private String county;

    /** 经营地址 **/
    private String address;

    /**  银行卡号 **/
    private String bankCardNo;

    /**  银行预留手机号 **/
    private String payerPhone;

    /** 开户行地址  **/
    private String bankAddress;

    /**  银行名称  **/
    private String bankName;

    /** 支行名称 **/
    private String bankSubbranch;

    /** 开户行省份 **/
    private String bankProv;

    /** 开户行城市 **/
    private String bankCity;

    /** 营业执照号 **/
    private String businessLicense;

    /**  微信T1费率 **/
    private String wxt1FeeRate;

    /**  微信T0费率 **/
    private String wxT0FeeRate;

    /**  支付宝T1 费率 **/
    private String alit1FeeRate;

    /**  支付宝T0 费率 **/
    private String alit0FeeRate;

    /**   储蓄卡费率 **/
    private String debitCardRate;

    /**  储蓄卡封顶 **/
    private String debitCardTopLimit;

    /**  信用卡费率 **/
    private String CreditCardRate;

    /**  是否为D0 商户 Y 是 N 否 **/
    private String D0falg;


    /** 商户类型：1 -- 标准   2 -- 优惠    3 减免类 **/
    private String mccCategory;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDCardNo() {
        return IDCardNo;
    }

    public void setIDCardNo(String IDCardNo) {
        this.IDCardNo = IDCardNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }


    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankSubbranch() {
        return bankSubbranch;
    }

    public void setBankSubbranch(String bankSubbranch) {
        this.bankSubbranch = bankSubbranch;
    }

    public String getBankProv() {
        return bankProv;
    }

    public void setBankProv(String bankProv) {
        this.bankProv = bankProv;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }


    public String getMerchantBillName() {
        return merchantBillName;
    }

    public void setMerchantBillName(String merchantBillName) {
        this.merchantBillName = merchantBillName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getWxt1FeeRate() {
        return wxt1FeeRate;
    }

    public void setWxt1FeeRate(String wxt1FeeRate) {
        this.wxt1FeeRate = wxt1FeeRate;
    }

    public String getWxT0FeeRate() {
        return wxT0FeeRate;
    }

    public void setWxT0FeeRate(String wxT0FeeRate) {
        this.wxT0FeeRate = wxT0FeeRate;
    }

    public String getAlit1FeeRate() {
        return alit1FeeRate;
    }

    public void setAlit1FeeRate(String alit1FeeRate) {
        this.alit1FeeRate = alit1FeeRate;
    }

    public String getAlit0FeeRate() {
        return alit0FeeRate;
    }

    public void setAlit0FeeRate(String alit0FeeRate) {
        this.alit0FeeRate = alit0FeeRate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public String getDebitCardRate() {
        return debitCardRate;
    }

    public void setDebitCardRate(String debitCardRate) {
        this.debitCardRate = debitCardRate;
    }

    public String getDebitCardTopLimit() {
        return debitCardTopLimit;
    }

    public void setDebitCardTopLimit(String debitCardTopLimit) {
        this.debitCardTopLimit = debitCardTopLimit;
    }

    public String getCreditCardRate() {
        return CreditCardRate;
    }

    public void setCreditCardRate(String creditCardRate) {
        CreditCardRate = creditCardRate;
    }

    public String getD0falg() {
        return D0falg;
    }

    public void setD0falg(String d0falg) {
        D0falg = d0falg;
    }

    public String getMccCategory() {
        return mccCategory;
    }

    public void setMccCategory(String mccCategory) {
        this.mccCategory = mccCategory;
    }
}
