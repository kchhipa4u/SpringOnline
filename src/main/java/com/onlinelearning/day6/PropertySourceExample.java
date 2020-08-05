package com.onlinelearning.day6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@PropertySource("classpath:app.properties")
public class PropertySourceExample {

    public static void main (String[] args) {
        AnnotationConfigApplicationContext context =
                            new AnnotationConfigApplicationContext(PropertySourceExample.class);
        ConfigurableEnvironment env = context.getEnvironment();
        System.out.println("some-strProp value is " + env.getProperty("name"));

        //printing all sources
        System.out.println(env.getPropertySources());
    }
}