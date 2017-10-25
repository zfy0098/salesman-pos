package com.rhjf.salesman.db;


import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by hadoop on 2017/10/24.
 *
 * @author hadoop
 */
@Repository
public class AgentDB extends DBBase {

    public Map<String,Object> agentInfo(String id){
        String sql = "select * from AGENT where id=?";
        return queryForMap(sql , new Object[]{id});
    }
}
