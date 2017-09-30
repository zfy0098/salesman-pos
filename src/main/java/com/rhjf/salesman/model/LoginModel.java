package com.rhjf.salesman.model;

/**
 * Created by hadoop on 2017/9/25.
 */
public class LoginModel extends  BeanBase {



    private String loginPWD;

    private String smsCode;

    public String getLoginPWD() {
        return loginPWD;
    }

    public void setLoginPWD(String loginPWD) {
        this.loginPWD = loginPWD;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
