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


*Step 10: Implementing DELETE Method to delete a User Resource*


* Implement delete functionality in *UserDaoService *Class

public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

* Make changes in *UserResource *class

@DeleteMapping("/users/{id}")
public void deleteUser(@PathVariable int id) {
    User user = service.deleteById(id);
        
      if(user==null)
          throw new UserNotFoundException("id-"+ id);        
 }


*Step 11: Implementing Validations for RESTful Services*


* Add below Spring boot Starter dependency 

This is needed for Spring boot 2.3.0.RELEASE or more, so in our case only below
dependency is needed
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
 </dependency>

 Below dependency is needed for 2.0.0.RELEASE or less 
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>

* Add a validation for a couple of attributes (name, birthDate) in User.java class

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;

    @Past
    private Date birthDate;

* add *@Valid* in  *createUser *method of UserResource.java class

@PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        // CREATED
        // /user/{id}     savedUser.getId()
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId()).toUri();
        
        return ResponseEntity.created(location).build();
        
    }

* Run program after above changes but it will not show proper validation message. It show *400 BAD Request*. To address this issue we need to enhance existing Generic Exception class *CustomizedResponseEntityExceptionHandler*

@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(
MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, 
WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
        "Validation Failed", ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

*Step 12: Implementing PUT Request*

* PUT is for the update operation
* Make the changes in our Service class method *save, *so that* *it can perform dual responsibility save and update both

public User saveOrUpdate(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
            users.add(user);
        } else {
            Iterator<User> iterator = users.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                User usr = iterator.next();
                if (usr.getId() == user.getId()) {
                    users.set(index, user);
                    break;
                }
                ++index;
            }
        }
        return user;
    }

* Introduce update method in *UserResource*.java class
* It’s ok not to send the location to the consumer. We can send message instead

//PUT http://localhost:8081/users/2
@PutMapping("/users/{id}")
      public ResponseEntity<Object> updateUser(@Valid @RequestBody User user, 
      @PathVariable int id) {

         User dbUser = service.findOne(id);
         
         if(dbUser==null)
                throw new UserNotFoundException("id-"+ id);

        user.setId(id);

        User savedUser = service.saveOrUpdate(user);

            
            return ResponseEntity.ok("resource updated");
      }


*Step 13 - Implementing HATEOAS for RESTful Services*

* HATEOAS is an acronym for *H*ypermedia *A*s *T*he *E*ngine *O*f *A*pplication *S*tate
* Add the below dependency in pom.xml

<dependency>
     <groupId>org.springframework.hateoas</groupId>
     <artifactId>spring-hateoas</artifactId>
    <version>0.23.0.RELEASE</version>
</dependency>

Don't use below Spring Boot dependency.
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-hateoas</artifactId>

* Modify *retrieveUser *method of* UserResource.java *class
* User Resource  class of hateoas API.

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        
        if(user==null)
            throw new UserNotFoundException("id-"+ id);
        
        
        //"all-users", SERVER_PATH + "/users"  (Don't use Hardcoded stuff)
        //retrieveAllUsers
        Resource<User> resource = new Resource<User>(user);
        
        ControllerLinkBuilder linkTo = 
                linkTo(methodOn(this.getClass()).retrieveAllUsers());
        
        resource.add(linkTo.withRel("all-users"));
        
        //HATEOAS
        
        return resource;
    }

	

![alt text](/resources/image/image_1.png)


Should I Use HATEOAS?

When it comes to architectural choices there are always tradeoffs. Before you consider using HATEOAS in the wild, you need to consider the pros and cons and whether or not you actually need it.

HATEOAS adds complexity to the API, which affects both the API developer and those who consume it. The API developer needs to handle the extra work of adding links to each response and providing the correct links based on the current application state. This results in an application that's more complex to build and test than a vanilla CRUD REST API.



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
     

