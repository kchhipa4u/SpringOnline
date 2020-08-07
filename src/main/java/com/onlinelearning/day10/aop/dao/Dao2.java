package com.onlinelearning.day10.aop.dao;

import org.springframework.stereotype.Repository;

@Repository
public class Dao2 {
	
	//@TrackTime
	public String retrieveSomething(){
		return "Dao2";
	}
}
