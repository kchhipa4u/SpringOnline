package com.onlinelearning.day7.component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 1. AppConfig is annotated with @ComponentScan("com.onlinelearning.day7.component")
@Configuration
@ComponentScan("com.onlinelearning.day7.component")
public class AppConfig {

    public static void main(String... strings) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("Spring container started and is ready");
        MyPrototypeBean bean = context.getBean(MyPrototypeBean.class);
        bean.doSomething();
    }
}