package com.onlinelearning.day7.component;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class ServiceImplB implements MyService {

    @PostConstruct
    private void init(){
        System.out.println("initializing at start up " +
                this.getClass().getSimpleName());
    }
    @Override
    public String getMessage() {
        return "Message from "+getClass().getSimpleName();
    }
}