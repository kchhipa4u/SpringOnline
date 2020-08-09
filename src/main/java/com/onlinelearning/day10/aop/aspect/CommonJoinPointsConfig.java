package com.onlinelearning.day10.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointsConfig {

	@Pointcut("@annotation(com.onlinelearning.day10.aop.TrackTime)")
	public void trackTimeAnnotation(){}
}
