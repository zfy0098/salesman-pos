package com.rhjf.salesman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class SalesmanApplication extends SpringBootServletInitializer{


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SalesmanApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SalesmanApplication.class, args);
	}
}
