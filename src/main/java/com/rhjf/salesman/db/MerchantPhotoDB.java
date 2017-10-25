package com.rhjf.salesman.db;

import org.springframework.stereotype.Repository;

/**
 * Created by hadoop on 2017/10/24.
 *
 * @author hadoop
 */
@Repository
public class MerchantPhotoDB extends DBBase{




    public int savePhotoInfo(Object[] obj){
        String sql = "insert into MERCHANTPHOTO (id , CUSTOMERNO , IDCARDFRONT , IDCARDREVERSE,IDCARDHOLD ,BUSINESSLICENSE , OPENINGLICENSE,STOREFRONT,INTERIOR,STREET,OTHER)" +
                "  values (SEQ_MERCHANTPHOTO_ID.nextval , ? ,? , ? ,? ,? , ? ,?,?,? ,?)";
        return jdbc.update(sql , obj);
    }






}
