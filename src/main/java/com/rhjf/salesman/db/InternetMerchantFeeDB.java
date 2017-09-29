package com.rhjf.salesman.db;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hadoop on 2017/9/26.
 */
@Repository
public class InternetMerchantFeeDB extends DBBase{




    public int saveMerchantFee(Object[] obj){
        String sql = "insert into INTERNETMERCHANT_FEE (ID,MERCHANTID,WXT1FEERATE , WXT0FEERATE,ALIT1FEERATE , ALIT0FEERATE , DEBITCARDRATE , DEBITCARDTOPLIMIT , CREDITCARDRATE) " +
                "VALUES (SEQ_INTERNETMERCHANT_FEE_ID.nextval , ?,?,?,?, ?,?,?,?)";

        return jdbc.update(sql , obj);
    }


}
