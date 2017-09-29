package com.rhjf.salesman.constant;

public class RespCode {

	public static final String[] SUCCESS = {"00", "请求成功"};
	
	public static final String[] userDoesNotExist = {"E000" , "账号或密码错误，请重新输入"};
	
	public static final String[] ParamsError = {"E001" , "参数错误"};
	
	public static final String[] TxndirError = {"E002" , "请求类型错误"};
	
	public static final String[] ServerDBError = {"E003" , "数据异常，请重新提交"};
	
	public static final String[] RegisterError = {"E004" , "注册失败,该账号已经存在"};
	
	public static final String[] PasswordError = {"E005"  ,  "账号或密码错误，请重新输入"};
	
	public static final String[] IMGSAVEError = {"E006" , "照片保存失败"};
	
	public static final String[] DATANOTEXISTError = {"E007" , "数据不存在"};
	
	public static final String[] SIGNMACError = {"E008" , "mac校验失败"};
	
	public static final String[] SMSCodeError = {"E009" , "短息验证码错误，请核对"};
	
	public static final String[] LOGINError = {"E010" , "该账号已被其他设备登录。若非本人操作，请修改密码。"};

	public static final String[] MerchantInfoError = {"E011" , "信息处于审核或通过审核状态，目前无法修改信息"};
	
	public static final String[] MerchantInfoStatusError = {"E012" , "信息没有通过审核,无法执行此操作"};
	
	public static final String[] AgentTradeConfigError = {"E013" , "代理商交易配置信息不完整，请联系客服人员"};
	
	public static final String[] AccountNoError = {"E014","结算卡账号只支持储蓄卡"};
	
	public static final String[] SYSTEMError = {"E015" , "服务器异常"};
	
	public static final String[] BankCardInfoErroe = {"E016" , "持卡人信息不一致，请确认信息是否正确"};

	public static final String[] EditMerchantLevelError = {"E017" , "该商户等级只能为铂金会员或钻石会员"};

	public static final String[] EditMerchantLevelError2 = {"E017" , "该商户等级只能为钻石会员"};

	public static final String[] EditMerchantLevelError3 = {"E018" , "30天内只允许修改一次会员等级"};

	public static final String[] PermissionDeniedError = {"E018" , "您无权执行此操作"};

	public static final String[] BindedErrir = {"E019" , "用户只能绑定自己代理商申请的固定码"};

	public static final String[] NETWORKError = {"E020" , "网络异常"};

	public static final String[] MerchantNameError = {"E021" , "商户名不和法，长度大于5位，并且小于12位；名称不能使用数字和英文"};

	public static final String[] TXAMOUNTError = {"T001" , "提现金额无效"};
	
	public static final String[] TXAMOUNTNOTENOUGH = {"T002" , "账户余额不足"};

	public static final String[] AlreadyInTheNetError = {"F001" , "该用户已经录入成功"};


}
