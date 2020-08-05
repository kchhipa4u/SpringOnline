package com.onlinelearning.day7.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.onlinelearning.day3.Employee;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyPrototypeBean {

    @Autowired
    @Qualifier("basic-service")
    private MyService myService;
    
    @Autowired
    private Employee emp;

    public void doSomething(){
        System.out.println(myService.getMessage());
        emp.getEmployeeInfo();
    }
}