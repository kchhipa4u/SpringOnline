package com.onlinelearning.day10.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// AOP
// Configuration
// Framework:- Weaver, Process :- Weaving

@Aspect
@Configuration
public class BeforeAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// What kind of method calls I want to intercept
	 // PACKAGE.Any_Class.Any_Method(for any argument)               
	//execution(* PACKAGE.*.*(..))
	//Weaving & Weaver
	
	// Below is called point-cut (The expression)
	// Method is called Advice:- and name of Advice is Before
	
	// A combination of Advice + pointcut = Aspect
	@Before("execution(* com.onlinelearning.day10.aop.service.*.doSomething(..))")
	public void before(JoinPoint joinPoint)
	{
	   // Logic: What to do?	
		logger.info(" Intercepted the method calls {} ", joinPoint);
	}
	
	// cover with point 6
	@Before("serviceLayerPointCut()")
	public void beforeServieLayer(JoinPoint joinPoint) {
		logger.info(" Before Pointcut on Service layer {} ", joinPoint);
	}
	
	//cover with point 6
	@Pointcut("execution(* com.onlinelearning.day10.aop.service.*.*(..))")
	public void serviceLayerPointCut(){}
}
