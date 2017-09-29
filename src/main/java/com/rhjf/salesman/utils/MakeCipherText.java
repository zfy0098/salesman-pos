package com.rhjf.salesman.utils;



/**
 * Created by hadoop on 2017/8/7.
 */
public class MakeCipherText {




    public  String calLoginPwd(String usrID,String pwd ,String sendTime , String protectINDEX){
        // 加密
        try {
            // 解析密码明文
            String keyde = new String(DESUtil.decrypt3(pwd, protectINDEX));
            return MD5.sign(usrID + sendTime + keyde.replace(" ", "") ,"utf-8" );

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     *   制作登录密码
     * @param pwd
     * @return
     */
    public  String MakeLoginPwd(String pwd,String initKeyoutStr){
//        //加密
        try {
            // 解析密钥明文
            pwd = DESUtil.rightPad(pwd, 16, " ");
            pwd = DESUtil.bytes2HexStr(pwd.getBytes(), false);
            return DESUtil.bcd2Str(DESUtil.encrypt3(pwd, initKeyoutStr));
        } catch (Exception e) {

            e.printStackTrace();
            return "";
        }
    }
}
