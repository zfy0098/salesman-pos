package com.rhjf.salesman.model;

import java.io.Serializable;

/**
 * 响应实体类
 * 
 * @author a
 *
 */
public class ResponseData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707742958465610382L;

	/** 登录账号 **/
	private String loginID;
	/** 终端发送时间 **/
	private String sendTime;

	/** 终端发送流水号 **/
	private String sendSeqID;

	/** 交易类型 **/
	private String txndir;

	/** 真实姓名 **/
	private String name;
	/** 身份证号 **/
	private String iDCardNo;
	/** 联系地址 **/
	private String address;

	/** 分润总额 **/
	private String feeAmount;
	
	/** 可体现金额  **/
	private String feeBalance;
	
	/**
	 * 信用卡分润总额
	 */
	private String cardFeeAmount;
	
	/**
	 * 信用卡可提现分润金额
	 */
	private String cardFeeBlance;
	
	
	/** 所在省份 **/
	private String state;

	/** 邮箱地址 **/
	private String email;

	/** 银行名称 **/
	private String bankName;
	
	/** 支行名称 **/
	private String bankSubbranch;
	
	/**  开户行所在省 **/
	private String bankProv;
	
	/** 开户行所在市  **/
	private String bankCity;

	/** 账号状态 **/
	private String accountStatus;
	/** 照片状态 **/
	private int photoStatus;

	private int bankInfoStatus;

	/** 银行卡号 **/
	private String bankCardNo;
	/** 银联号 **/
	private String bankNo;

	/** 商户等级 **/
	private int level;

	/** 商户名称 **/
	private String merchantName;

	/** 响应状态码 **/
	private String respCode;

	/** 响应消息 **/
	private String respDesc;

	/**
	 * 信用卡 开通银行list
	 */
	private String bankList;
	/**
	 * 信用卡公网链接
	 */
	private String bankUrl;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * APP链接
	 */
	private String appUrl;

	/**
	 * APP版本修改信息
	 */
	private String updateInfo;

	/** 支付二维码地址 **/
	private String qrCodeUrl;

	/** app 版本信息 **/
	private String appInfo;

	/** 终端信息 **/
	private String terminalInfo;

	/** 返回请求的集合 **/
	private String translist;

	/** 支付类型编号 **/
	private String payChannel;

	/** 手持身份证照片 **/
	private String handheldIDPhoto;

	/** 身份证正面照片 **/
	private String iDCardFrontPhoto;

	/** 身份证反面照片 **/
	private String iDCardReversePhoto;

	/** 银行卡照片 **/
	private String bankCardPhoto;

	/** 营业执照照片 **/
	private String businessPhoto;
	
	/** 签名mac **/
	private String mac;
	
	/**
	 * 鉴权记录列表
	 */
	private String authenList;
	
	/**
	 * 数据结果集合
	 */
	private String list;
	

	/** 拒绝审核原因 **/
	private String remarks;

	 //订单号
	private String orderNumber;

	//商户号
	private String merchantNo;
	
	
	/** 当前页数 **/
	private Integer page;
	
	/** 显示总条数  **/
	private Integer totalCount;
	
	/** 当天交易额 **/
	private String today;
	
	/** 总交易额 **/
	private String total;
	
	/**8 交易到账类型 **/
	private String tradeCode;
	
	/**  交易总笔数 ***/
	private String tradeCount;
	
	/**  手续费 ***/
	private String fee;
	
	/**  纯利润 **/
	private String pureProfit;
	
	/**  用户签到签到获取秘钥  **/
	private String secretKey;
	
	/** 信用卡账号 **/
	private String creditCardNo;
	
	/**  app 敏感信息开关  0 关 1开  **/
	private String open;
	
	/**  商户注册时间 **/
	private String registerDate;
	
	/** MPOS 设备sn 号 **/
	private String sn;
	
	
	/** 拓客金额 **/
	private String tokerAmount;
	
	/** 返利金额 **/
	private String antiAmount;
	
	
	/**  费率 **/
	private String rate;


	/**  关于我们 **/
	private String aboutURL;


	/**  公司资质 **/
	private String companyAptitudeURL;



	/**  业务员总收益 **/
	private String profitTotal;


	/**  卡类型 **/
	private String cardName;


	/**  银行缩写 **/
	private String bankSymbol;


	/**  商户号 **/
	private String customerNo;


	/**  信用卡申请地址 **/
	private String creditURL;


	/**  文字消息 **/
	private String message;


	/**  商户信息的开关  **/
	private String shopInfo;

	
	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}

	public String getTerminalInfo() {
		return terminalInfo;
	}

	public void setTerminalInfo(String terminalInfo) {
		this.terminalInfo = terminalInfo;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getSendSeqID() {
		return sendSeqID;
	}

	public void setSendSeqID(String sendSeqID) {
		this.sendSeqID = sendSeqID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public int getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(int photoStatus) {
		this.photoStatus = photoStatus;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getTranslist() {
		return translist;
	}

	public void setTranslist(String translist) {
		this.translist = translist;
	}

	public String getBankList() {
		return bankList;
	}

	public void setBankList(String bankList) {
		this.bankList = bankList;
	}

	public String getBankUrl() {
		return bankUrl;
	}

	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}

	public String getTxndir() {
		return txndir;
	}

	public void setTxndir(String txndir) {
		this.txndir = txndir;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getUpdateInfo() {
		return updateInfo;
	}

	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}

	public int getBankInfoStatus() {
		return bankInfoStatus;
	}

	public void setBankInfoStatus(int bankInfoStatus) {
		this.bankInfoStatus = bankInfoStatus;
	}

	public String getHandheldIDPhoto() {
		return handheldIDPhoto;
	}

	public void setHandheldIDPhoto(String handheldIDPhoto) {
		this.handheldIDPhoto = handheldIDPhoto;
	}

	public String getBankCardPhoto() {
		return bankCardPhoto;
	}

	public void setBankCardPhoto(String bankCardPhoto) {
		this.bankCardPhoto = bankCardPhoto;
	}

	public String getBusinessPhoto() {
		return businessPhoto;
	}

	public void setBusinessPhoto(String businessPhoto) {
		this.businessPhoto = businessPhoto;
	}

	public String getiDCardNo() {
		return iDCardNo;
	}

	public void setiDCardNo(String iDCardNo) {
		this.iDCardNo = iDCardNo;
	}

	public String getiDCardFrontPhoto() {
		return iDCardFrontPhoto;
	}

	public void setiDCardFrontPhoto(String iDCardFrontPhoto) {
		this.iDCardFrontPhoto = iDCardFrontPhoto;
	}

	public String getiDCardReversePhoto() {
		return iDCardReversePhoto;
	}

	public void setiDCardReversePhoto(String iDCardReversePhoto) {
		this.iDCardReversePhoto = iDCardReversePhoto;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankSubbranch() {
		return bankSubbranch;
	}

	public void setBankSubbranch(String bankSubbranch) {
		this.bankSubbranch = bankSubbranch;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getAuthenList() {
		return authenList;
	}

	public void setAuthenList(String authenList) {
		this.authenList = authenList;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
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

	public String getCardFeeAmount() {
		return cardFeeAmount;
	}

	public void setCardFeeAmount(String cardFeeAmount) {
		this.cardFeeAmount = cardFeeAmount;
	}

	public String getCardFeeBlance() {
		return cardFeeBlance;
	}

	public void setCardFeeBlance(String cardFeeBlance) {
		this.cardFeeBlance = cardFeeBlance;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(String tradeCount) {
		this.tradeCount = tradeCount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getPureProfit() {
		return pureProfit;
	}

	public void setPureProfit(String pureProfit) {
		this.pureProfit = pureProfit;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getTokerAmount() {
		return tokerAmount;
	}

	public void setTokerAmount(String tokerAmount) {
		this.tokerAmount = tokerAmount;
	}

	public String getAntiAmount() {
		return antiAmount;
	}
	public void setAntiAmount(String antiAmount) {
		this.antiAmount = antiAmount;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}


	public String getAboutURL() {
		return aboutURL;
	}

	public void setAboutURL(String aboutURL) {
		this.aboutURL = aboutURL;
	}

	public String getCompanyAptitudeURL() {
		return companyAptitudeURL;
	}

	public void setCompanyAptitudeURL(String companyAptitudeURL) {
		this.companyAptitudeURL = companyAptitudeURL;
	}

	public String getProfitTotal() {
		return profitTotal;
	}

	public void setProfitTotal(String profitTotal) {
		this.profitTotal = profitTotal;
	}


	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getBankSymbol() {
		return bankSymbol;
	}

	public void setBankSymbol(String bankSymbol) {
		this.bankSymbol = bankSymbol;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCreditURL() {
		return creditURL;
	}

	public void setCreditURL(String creditURL) {
		this.creditURL = creditURL;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(String shopInfo) {
		this.shopInfo = shopInfo;
	}
}
