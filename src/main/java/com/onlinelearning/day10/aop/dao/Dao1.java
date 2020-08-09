package com.onlinelearning.day10.aop.dao;

import org.springframework.stereotype.Repository;

import com.onlinelearning.day10.aop.TrackTime;

@Repository
public class Dao1 {
	
	@TrackTime
	public String retrieveSomething(){
		return "Dao1";
	}

}
