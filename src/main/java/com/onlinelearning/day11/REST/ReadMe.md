Spring REST

Let’s Get Our Hand Dirty

[Image: image.png]
*Step1:- Create Class to start Spring Boot application*

@SpringBootApplication
public class RESTApplication {    

    public static void main(String[] args) {
 ConfigurableApplicationContext context = SpringApplication.run(RESTApplication.class, args);
        
    }

}

*Step2:- Write your First REST application*


mport org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

    // GET
    // URI - /hello-world
    //method - "Hello World"
    //@RequestMapping(method = RequestMethod.GET , path = "/hello-world") : 1st Way
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}

*Step3:- Let’s return the message using Bean class*
 a. Create Bean Class First

public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    // Don't use getters first. It will throw an error during access
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }

}

b. Add below method in Controller class

@GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }


[Image: image.png]
We can turn on debug level logging for the Spring application by using below property.

logging.level.org.springframework = debug


*Step4:- Enhancing the Hello World Service with a Path Variable*


///hello-world/path-variable/kanhaiya
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }


*Step5: Creating User Bean and User Service*
Let’s do some real-world implementation

a. Create User Class


public class User {

    private Integer id;

    private String name;

    private Date birthDate;

    public User(Integer id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
    }

}

b. Create User Service class


@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Aanya", new Date()));
        users.add(new User(2, "Prayan", new Date()));
        users.add(new User(3, "Ayaan", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}

c. Now, it’s time to create Rest Resource class, and test it.

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

}

Sometime date does not display on browser properly. In some older version of Spring boot it shows timestamp only. So, to fix it we need to add below property in application.properties file


#This is not really needed as this is the default after 2.0.0.RELEASE
spring.jackson.serialization.write-dates-as-timestamps=false
spring.messages.basename=messages


*Step6: Implementing POST Method to create User Resource*
Let’s add the below method to the UserResource class.
Rest Client :- Postman

// input - details of user
    // output - CREATED & Return the created URI
    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        User savedUser = service.save(user);
    }


*Step7: Enhancing POST Method to return correct HTTP Status Code and Location URI*

Below code will generate status code 201 and URI Location as well in postman

/
    // input - details of user
    // output - CREATED & Return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        // CREATED
        // /user/{id}     savedUser.getId()
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId()).toUri();
        
        return ResponseEntity.created(location).build();
        
    }


*Step8:  Implementing Exception Handling - 404 Resource Not Found*


* First Create a Custom Exception class.
* Don’t use below annotation First:- It will give 500 Internal Server Error (Which is not best practice)
    * @ResponseStatus(HttpStatus.NOT_FOUND)



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

* Enhance existing retrieveUser method in UserResource class

@GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        
        if(user==null)
            throw new UserNotFoundException("id-"+ id);
        
        return user;
    }


*Step9 : Implementing Generic Exception Handling for all Resources*


* This is Standard practice performed in Enterprise level application.
* We need to create Generic Exception class First

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}

* Create a Customized ResponseEntityExceptionHandler  class which have all kind of exception statuses.
* @*ControllerAdvice *: Specialization of @Component for classes that declare @ExceptionHandler, @InitBinder, or @ModelAttribute methods to be shared across multiple @Controller classes. 
* *ResponseEntityExceptionHandler*: A convenient base class for @ControllerAdvice classes that wish to provide centralized exception handling across all @RequestMapping methods through @ExceptionHandler methods. 

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}



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
     

