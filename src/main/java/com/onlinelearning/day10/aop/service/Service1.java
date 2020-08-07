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
	
	//@TrackTime
	public String doSomething(){
		//Business Logic
		String value = dao1.retrieveSomething();
		logger.info("In Business - {}", value);
		return value;
	}

}
