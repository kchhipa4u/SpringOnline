package com.onlinelearning.day5;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AutowireByType {

    @Configuration
    public static class Config {

    	// 1. First Change
        @Bean(autowire = Autowire.BY_TYPE)
        public Employee employeeBean () {
            return new Employee();
        }

        @Bean
        public Address addressBean1 () {
            return new Address("Mumbai, Maharastra");
        }

		// 4. Ambiguity problem exists here as well 
		
        @Qualifier("bean2")
        @Bean(name = "bean2" )
		public Address addressBean2() {
			return new Address("Banglore, Karnataka");
		}
		 
		 
    }

    private static class Employee {
        
    	// 2. In this mode field injection doesn't work
        private Address address;
        
       /*
        * 3. There must be a setter. Spring scans all setters of a bean and if the type of property matches and there is 
        * no ambiguity then injects the target property
        */
        @Qualifier("bean2")
        public void setAddress(Address address) {
			this.address = address;
		}

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