package com.onlinelearning.day8.conditional;

import java.util.Locale;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanConditionTest {

    public static void main (String[] args) {

        runApp(Locale.US);
        System.out.println("----------");
        runApp(Locale.CANADA);
    }

    public static void runApp (Locale locale) {

        /**In real application, Locale will be set with your machine
         * depending on your location.
         * Here we are setting it manually to test our example*/
        System.out.printf("setting default locale: %s\n", locale);
        Locale.setDefault(locale);

        AnnotationConfigApplicationContext context =
                            new AnnotationConfigApplicationContext(
                                                AppConfig.class);

        ClientBean bean = context.getBean(ClientBean.class);
        System.out.printf("Injected MyService instance in ClientBean: %s\n", bean.getMyService()
                                                                              .getClass()
                                                                              .getSimpleName());
    }
}