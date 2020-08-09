
## 1.  Add Dependency First:
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		
2. Write Service classes in their folder: Service1, Service2

3. Write DAO classes in their folder: Dao1, Dao2

4. Write the SpringAopApplication class which extends CommandLineRunner, and you logger as well using Logback

5. Write your first Aspect class named Before Aspect. Also understand key Aspect Terminology

       // PACKAGE.Any_Class.Any_Method(for any argument)               
	   //execution(* PACKAGE.*.*(..))
	   a. test without JoinPoint Argument
	   b. Test with Joinpoint Argument
	   
## 6. Spring AOP Pointcut Methods and Reuse
	Sometimes we have to use same Pointcut expression at multiple places, we can create an empty method with @Pointcut 
	annotation and then use it as an expression in the advices.	  
	 ```
	 // cover with point 6
	@Before("serviceLayerPointCut()")
	public void beforeServieLayer(JoinPoint joinPoint) {
		logger.info(" Before Pointcut on Service layer {} ", joinPoint);
	}
	
	//cover with point 6
	@Pointcut("execution(* com.onlinelearning.day10.aop.service.*.*(..))")
	public void serviceLayerPointCut(){}
	
	``` 
	   
7. Write AfterAspect class which covers both @After, and @AfterReturning Aspects
     -- @After is always called regardless of @AfterReturning or @AfterThrowing.

	