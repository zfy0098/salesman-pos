package com.rhjf.salesman.db;


import org.springframework.stereotype.Repository;

/**
 * Created by hadoop on 2017/10/17.
 */
@Repository
public class OnlinePayMerchantDB extends DBBase{



    public int saveMerchantTradeKey(Object[] params){

        String sql = "insert into ONLINE_PAY_MERCHANT (ID , customer_no , merchant_no , customer_name, " +
                "signkey ,deskey , querykey , paytype , t1rate , t0rate , reg_flag , creation , last_update , version , ALIT1RATE, ALIT0RATE) " +
                "values (seq_online_pay_merchant_id.nextval , ?,?,?,?,?,?,'WX|ALI' ,?,?,'Y' ,sysdate , sysdate , 1 , ? ,?)" ;

        return jdbc.update(sql , params);
    }



    public int initOnlineMerchantKey(Object[] obj){
        String sql = "insert into ONLINE_PAY_MERCHANT (id ,customer_no , customer_name  ," +
                " paytype , t1rate , t0rate , reg_flag , creation , last_update , version , ALIT1RATE, ALIT0RATE) values " +
                " (SEQ_ONLINE_PAY_MERCHANT_ID.nextval , ?,?, 'WX|ALI' ,?,?, 'Y' ,sysdate,sysdate , 1 , ?,? )";
        return jdbc.update(sql , obj);
    }
}
