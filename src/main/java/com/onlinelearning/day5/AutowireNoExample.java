package com.onlinelearning.day5;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AutowireNoExample {

    @Configuration
    public static class Config {

        @Bean(autowire = Autowire.NO)
        public Employee employeeBean () {
            return new Employee();
        }

        @Bean(name = "bean1" )
        public Address addressBean1 () {
            return new Address("Mumbai, Maharastra");
        }
        
        
        /*
         * There shouldn't be any conflict (ambiguity), which means there should be no more than one bean instance of the same type registered for a 
         * given injection point, otherwise we will have NoUniqueBeanDefinitionException.
         * 
         * To avoid exception, we have to use @Qualifier
         */
        @Bean(name = "bean2")
        public Address addressBean2 () {
            return new Address("Banglore, Karnataka");
        }
    }

    private static class Employee {
        
    	@Autowired
    	 @Qualifier("bean2")
        private Address address;

        public void getEmpAddress () {
            System.out.println(address.getAddr());
        }
    }

    private static class Address {
        private String addr;

        public Address (String addr) {
            this.addr = addr;
        }

        public String getAddr () {
            return addr;
        }
    }
    
    public static void main (String[] args) {
        AnnotationConfigApplicationContext context = new
                            AnnotationConfigApplicationContext(Config.class);
        Employee bean = context.getBean(Employee.class);
        bean.getEmpAddress();
    }
}