package com.onlinelearning.day7.component;

//2. All beans are annotated with @Component, except for MyService which is just an interface
// 3. There are two implementations of MyService : ServiceImplA and ServiceImplB. So to avoid ambiguity,
//we named ServiceImplA by using @Component("basic-service") .  We could have used @Qualifier instead for naming the bean component:
public interface MyService {

    String getMessage();
}