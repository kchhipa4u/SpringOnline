package com.onlinelearning.day6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:app.properties")
public class BeanValueExample {
    @Bean
    public MyBean myBean () {
        return new MyBean();
    }

	/*
	 * @Bean public static PropertySourcesPlaceholderConfigurer propertyConfigInDev
	 * () { System.out.println("KKKK"); return new
	 * PropertySourcesPlaceholderConfigurer(); }
	 */

    public static void main (String[] args) {
        AnnotationConfigApplicationContext context =
                            new AnnotationConfigApplicationContext(BeanValueExample.class);
        context.getBean(MyBean.class)
               .showProp();
        
        ConfigurableEnvironment env = context.getEnvironment();
        
        //printing all sources
        System.out.println(env.getPropertySources());
    }

    
    public static class MyBean {
        @Value("${name}")
        private String str;
        
        @Value("#{systemProperties['user.home']}")
        private String userHome;

        
        @Value("${occupation}")
        private String occupation;
        
        @Value("${age:35}") 
        private String age;
        
        @Autowired
        Environment env;
        

        public void showProp () {
            System.out.println(str);
            System.out.println(occupation);
            
            System.out.println(userHome);
            
            System.out.println(env.getProperty("occupation"));
            System.out.println(age);
            
        }
    }
    
}