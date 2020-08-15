## 1. Write AdminRestController with all HTTP Methods
## 2. SpringBoot Starter RESTApplication.java

Test with below URL:-
http://localhost:8080/admin/show

## 3. Now change the port and context-root path using application.properties
	server.port=8081
	server.servlet.context-path=/myRest
	
	Now retest with below URL
	http://localhost:8081/myRest/admin/show
	
## 4. Example2: Read Data from Http Request (Head and Body)

     Write AdminRestController1.java class
     

