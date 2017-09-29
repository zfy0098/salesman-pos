package com.rhjf.salesman.service;

import com.rhjf.salesman.db.SalesmanKeyDB;
import com.rhjf.salesman.db.SalesmanLoginDB;
import com.rhjf.salesman.model.SalesmanKey;
import com.rhjf.salesman.model.SalesmanLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hadoop on 2017/9/26.
 */


@Service("SalesmanInfoService")
public class SalesmanInfoService {



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

        SalesmanLogin salesmanLogin = salesmanLoginDB.salesmanInfo(loginID);

        return salesmanLogin;

    }

    /**
     *   获取业务员秘钥
     * @param salesmanID
     * @return
     */
    public SalesmanKey userTermkey(String salesmanID){

        SalesmanKey salesmanKey = salesmanKeyDB.salesmanKeyInfo(salesmanID);

        return salesmanKey;
    }



}
