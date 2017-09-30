package com.rhjf.salesman.controller;

import com.rhjf.salesman.utils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hadoop on 2017/9/28.
 */
@Controller
@ResponseBody
@RequestMapping("/test")
public class TestController {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Value("${aboutURL}")
    private String aboutURL;

    @RequestMapping("")
    public Object test(){


        log.info("aboutURL :" + aboutURL);

        log.info("测试类");
        log.error("错误日志");
        return "123";
    }
}
