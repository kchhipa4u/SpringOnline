package com.onlinelearning.day7.component;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/*
 * We cud have used below as well
 * @Component
@Qualifier("basic-service")
public class ServiceImplA implements MyService

ServiceImplA is also annotated with @Lazy so it's @PostConstruct method will only be called when used first time rather than at start up.
 */

@Lazy
@Component("basic-service")
public class ServiceImplA implements MyService {

    @PostConstruct
    private void init() {
        System.out.println("initializing lazily " +
                this.getClass().getSimpleName());
    }

    @Override
    public String getMessage() {
        return "Message from " + getClass().getSimpleName();
    }
}