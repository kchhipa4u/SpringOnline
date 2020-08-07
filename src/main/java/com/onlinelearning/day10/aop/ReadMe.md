
1.  Add Dependency First:
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
	   
6. Write AfterAspect class which covers both @After, and @AfterReturning Aspects
     -- @After is always called regardless of @AfterReturning or @AfterThrowing.

	