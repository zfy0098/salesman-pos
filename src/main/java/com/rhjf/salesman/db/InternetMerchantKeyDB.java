package com.rhjf.salesman.db;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hadoop on 2017/9/26.
 */
@Repository
public class InternetMerchantKeyDB extends DBBase{



    public int[] saveMerchantTradeKey(List<Object[]> list){

        String sql = "insert into INTERNETMERCHANTKEY (ID , MERCHANTID , MERCHANTNO , DESKEY,SIGNKEY,QUERYKEY,TRADETYPE )" +
                "values (SEQ_INTERNETMERCHANTKEY_ID.nextval , ?, ?, ?, ?, ? ,?)" ;
        return jdbc.batchUpdate(sql , list);
    }

}
