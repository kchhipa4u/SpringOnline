package com.onlinelearning.day10.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onlinelearning.day10.aop.service.Service1;
import com.onlinelearning.day10.aop.service.Service2;

@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Service1 service1;

	@Autowired
	private Service2 service2;

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(service1.doSomething());
		logger.info(service2.doSomething());
		
		// cover with point 6
		//logger.info(service1.doM1());
		//logger.info(service1.doM2());
		
		//service1.doGetException();
	}
}
