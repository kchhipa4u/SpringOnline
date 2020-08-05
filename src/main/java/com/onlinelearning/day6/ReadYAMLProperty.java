package com.onlinelearning.day6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class ReadYAMLProperty {


    @Bean
    YmlBeam ymlBean() {
        return new YmlBeam();
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(ReadYAMLProperty.class, args);
        YmlBeam myBean = context.getBean(YmlBeam.class);
        myBean.startApplication();
    }

    private static class YmlBeam {

        @Value("${emp.title}")
        private String appTitle;
        
        @Value("${emp.salary}")
        private String empSalary;

        public void startApplication() {
            System.out.printf("-- running application: %s --%n", appTitle);
            System.out.printf("-- running application: %s --%n", empSalary);

        }
    }

}
