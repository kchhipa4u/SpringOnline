
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
Sometimes we have to use same Pointcut expression at multiple places, we can create an empty method with @Pointcut annotation and then use it as an expression in the advices.
	
```
	@Before("serviceLayerPointCut()")
	public void beforeServieLayer(JoinPoint joinPoint) {
		logger.info(" Before Pointcut on Service layer {} ", joinPoint);
	}
	
	@Pointcut("execution(* com.onlinelearning.day10.aop.service.*.*(..))")
	public void serviceLayerPointCut(){}
```
	   
7. Write AfterAspect class which covers both @After, and @AfterReturning Aspects
     -- @After is always called regardless of @AfterReturning or @AfterThrowing.
     * *After returning advice*: Advice to be executed after a join point completes normally: for example, if a method returns without throwing an exception.
     * *After throwing advice*: Advice to be executed if a method exits by throwing an exception.
     * *After (finally) advice:* Advice to be executed regardless of the means by which a join point exits (normal or exceptional return).

## 8. @AfterThrowing

a. Add Exception related method in Service1 cass Like given below.
b. Call that method in SpringAopApplication.java as well to make sure that method is being called for testing purpose.

```
    // AfterAspect
	@AfterThrowing("execution(* com.onlinelearning.day10.aop.service.*.*(..))")
	public void logExceptions(JoinPoint joinPoint){
		logger.info("Exception thrown in doGetException Method="+joinPoint.toString());
	}
	// Service1.java
	public void doGetException() {
			int x = 10/0;
	}
	// SpringAopApplication.java
	service1.doGetException();
```
## 9. Around Aspect Example
we can use Around aspect to cut the method execution before and after. We can use it to control whether the advised method will execute or not. 
We can also inspect the returned value and change it. This is the most powerful advice and needs to be applied properly.

CalcMethodExecTimeByAroundAspect.java

Around advice are always required to have ProceedingJoinPoint as an argument and we should use it’s proceed() method to invoke the target object advised method.

Around advice is the most general kind of advice. 
Since Spring AOP, like AspectJ, provides a full range of advice types, we recommend that you use the least powerful advice type that can implement the required behavior. 
For example, if you need only to update a cache with the return value of a method, you are better off implementing an after returning advice than an around advice, although an around advice can accomplish the same thing. Using the most specific advice type provides a simpler programming model with less potential for errors. 
For example, you do not need to invoke the proceed() method on the JoinPoint used for around advice, and hence cannot fail to invoke it.


## 10. Performance Improvements
We can create a Common class which includes all pointcut expressions

```
    @Pointcut("execution(* com.onlinelearning.day10.aop.dao.*.*(..))")
	public void dataLayerExecution(){}
	
	@Pointcut("execution(* com.onlinelearning.day10.aop.service.*.*(..))")
	public void serviceLayerExecution(){}
	
	// To include aa Layers
	@Pointcut("dataLayerExecution() && serviceLayerExecution()")
	public void allLayerExecution(){}
	
	@Pointcut("bean(*dao*)")
	public void beanContainingDao(){}
	
	@Pointcut("within(com.in28minutes.spring.aop.springaop.data..*)")
	public void dataLayerExecutionWithWithin(){}

```
## 11. Advice with Custom Annotation Pointcut

Define new annotation class and write separate class
```

    @Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		public @interface TrackTime {
	}
	
    public class CommonJoinPointsConfig {
		@Pointcut("@annotation(com.onlinelearning.day10.aop.TrackTime)")
		public void trackTimeAnnotation(){}
    }
  
    Apply @TrackTime methods of Dao1 and Dao2 class methods
```



	
