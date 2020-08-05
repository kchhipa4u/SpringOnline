package com.onlinelearning.day8.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    @Conditional(LocaleConditionUSA.class)
    public MyService myBeanA () {
        return new ServiceImplA();
    }

    @Bean
    @Conditional(LocaleConditionCanada.class)
    public MyService myBeanB () {
        return new ServiceImplB();
    }

    @Bean
    public ClientBean clientBean () {
        return new ClientBean();
    }
}