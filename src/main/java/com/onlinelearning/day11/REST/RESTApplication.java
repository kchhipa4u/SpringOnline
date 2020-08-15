package com.onlinelearning.day11.REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RESTApplication {
	

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RESTApplication.class, args);
		
	}

}
