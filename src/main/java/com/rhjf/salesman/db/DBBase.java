package com.rhjf.salesman.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * Created by hadoop on 2017/9/25.
 *
 * @author hadoop
 *
 */

@Repository
public class DBBase {


    @Autowired
    public JdbcTemplate jdbc;


    public static JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = jdbc;
    }


    protected  Map<String,Object> queryForMap(String sql , Object... obj ){

        List<Map<String,Object>> list = jdbc.queryForList(sql , obj );

        if(list == null || list.size() == 0 ){
            return null;
        }else {
            return list.get(0);
        }
    }
}
