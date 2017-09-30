package com.rhjf.salesman.service;

import com.rhjf.salesman.db.SalesmanKeyDB;
import com.rhjf.salesman.db.SalesmanLoginDB;
import com.rhjf.salesman.model.SalesmanKey;
import com.rhjf.salesman.model.SalesmanLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hadoop on 2017/9/26.
 */


@Service("SalesmanInfoService")
public class SalesmanInfoService {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SalesmanLoginDB salesmanLoginDB;


    @Autowired
    private SalesmanKeyDB salesmanKeyDB;


    /**
     *   获取业务员信息
     * @param loginID
     * @return
     */
    public SalesmanLogin SalesmanInfo(String loginID){

        try {
            return salesmanLoginDB.salesmanInfo(loginID);
        }catch (Exception e){
            log.info("转换java bean 异常："  , e) ;
            return null;
        }
    }

    /**
     *   获取业务员秘钥
     * @param salesmanID
     * @return
     */
    public SalesmanKey userTermkey(Integer salesmanID){

        SalesmanKey salesmanKey = salesmanKeyDB.salesmanKeyInfo(salesmanID);

        return salesmanKey;
    }



}
