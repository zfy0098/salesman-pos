package com.rhjf.salesman.db;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 2017/9/26.
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
                "COUNTY , ADDRESS , BUSINESSLICENSE , BANKCARDNO , BANKNAME , BANKCODE , BANKBRANCH , D0_FALG , MCC_CATEGORY) VALEUS " +
                "(SEQ_INTERNETMERCHANT_ID.nextval , ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        this.jdbcTemplate.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

        String sql = "update  set IDCARDFRONT=? , IDCARDREVERSE=? , IDCARDHOLD=? where ID=?";

        return jdbc.update(sql , obj);
    }



}
