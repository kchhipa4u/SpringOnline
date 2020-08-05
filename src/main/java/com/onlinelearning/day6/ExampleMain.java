package com.onlinelearning.day6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@SpringBootConfiguration
public class ExampleMain {
    @Bean
    SomeBean someBean() {
        return new SomeBean();
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(ExampleMain.class, args);
        SomeBean myBean = context.getBean(SomeBean.class);
        myBean.startApplication();
    }

    private static class SomeBean {

        @Value("${app.title}")
        private String appTitle;

        public void startApplication() {
            System.out.printf("-- running application: %s --%n", appTitle);

        }
    }
}