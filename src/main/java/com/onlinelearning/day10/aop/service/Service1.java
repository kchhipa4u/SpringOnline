package com.onlinelearning.day10.aop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearning.day10.aop.dao.Dao1;

@Service
public class Service1 {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Dao1 dao1;

	// @TrackTime
	public String doSomething() {
		// Business Logic
		String value = dao1.retrieveSomething();
		logger.info("In Business - {}", value);
		return value;
	}

	public String doM1() {
          logger.info("Inside doM1()...");
          return "returning from doM1()";
	}

	public String doM2() {
		 logger.info("Inside doM2()...");
         return "returning from doM2()";
	}
	
	public void doGetException() {
			int x = 10/0;
	}

}
