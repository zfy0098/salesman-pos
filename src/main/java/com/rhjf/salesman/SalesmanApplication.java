package com.rhjf.salesman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * Created by hadoop on 2017/10/31.
 *
 * @author hadoop
 */

@SpringBootApplication
public class SalesmanApplication  extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(SalesmanApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SalesmanApplication.class, args);
	}
}
