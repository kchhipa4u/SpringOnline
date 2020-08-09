package com.onlinelearning.day10.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class CalcMethodExecTimeByAroundAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.onlinelearning.day10.aop.service.*.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		Object returnProceed = joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time Taken by {} is {} ms", joinPoint, timeTaken);

		return returnProceed;
	}
	
	@Around("com.onlinelearning.day10.aop.aspect.CommonJoinPointsConfig.trackTimeAnnotation()")
	public Object customTimeTracking(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		Object returnProceed = joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time Taken by {} is {} ms", joinPoint, timeTaken);

		return returnProceed;
	}
}