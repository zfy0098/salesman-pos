package com.rhjf.salesman.db;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 2017/9/26.
 *
 * @author hadoop
 */
@Repository
public class InternetMerchantDB extends DBBase{


    /**
     *   保存商户信息
     * @param obj
     * @return
     */
    public int saveInternetMerchantInfo(final Object[] obj){


        KeyHolder keyHolder = new GeneratedKeyHolder();

        final String sql = "insert into INTERNETMERCHANT (ID,SALESMANID , NAME , MERCHANTNAME , MERCHANTPERSONNAME , IDCARDNO,STATE,CITY," +
                "COUNTY , ADDRESS , BUSINESSLICENSE , BANKCARDNO , BANKNAME , BANKCODE , BANKBRANCH , D0_FALG ," +
                " MCC_CATEGORY , CUSTOMERNO ,POSSTATUS , ONLINESTATUS , BUSINESSSCOPE , BUSINESSREGISTNAME,ORGANIZATIONCODE,CERTIFICATEREGIST , ACCOUNTNAME , CREATEDATE , CREDITCARDNO) VALUES " +
                "(SEQ_INTERNETMERCHANT_ID.nextval , ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? , ? ,? ,?,? , ?,?, ? ,? ,sysdate , ?)";

        this.jdbc.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
                for(int i = 0; i < obj.length; i++) {
                    ps.setString(i + 1, obj[i].toString());
                }
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }


    /**
     *    商户列表
     * @param salesmanID
     * @return
     */
    public List<Map<String,Object>> merchantList(Integer salesmanID){
        String sql = "select * from INTERNETMERCHANT where SALESMANID = ?";
        return jdbc.queryForList(sql , new Object[]{salesmanID});
    }


    /**
     *      更新商户照片信息
     * @param obj
     * @return
     */
    public int updateMerchantPhotoInfo(Object[] obj){

        String sql = "update internetmerchant  set IDCARDFRONT=? , IDCARDREVERSE=? , IDCARDHOLD=? where ID=?";

        return jdbc.update(sql , obj);
    }



}
