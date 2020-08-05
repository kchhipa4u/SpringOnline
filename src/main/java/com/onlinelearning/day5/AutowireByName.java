package com.onlinelearning.day5;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AutowireByName {

    @Configuration
    public static class Config {

    	// 1. First Change
        @Bean(autowire = Autowire.BY_NAME)
        public Employee employeeBean () {
            return new Employee();
        }

        @Bean
        public Address addressBean1 () {
            return new Address("Mumbai, Maharastra");
        }

		// 3. No Ambiguity problem
		// the beans are registered as the 'method name' annotated with @Bean unless we use the 'name' element of @Bean.
		@Bean
		public Address addressBean2() {
			return new Address("Noida, Uttar Pradesh");
		}
		
		// 4. We can also specify an explicit bean name using 'name' element of the @Bean
		// For testing purpose we need to change Bean name to customAddress
		@Bean(name = "customAddress")
		public Address addressBean3() {
			return new Address("New Delhi, Delhi");
		}
		 
		 
    }

    private static class Employee {
        
    	// 2. We have to use @Autowired at the injection point in this mode.
    	// We can use setter based or constructor based autowiring as well, still the filed name (bean property) has to match
    	@Autowired
        private Address customAddress;
        

		public void getEmpAddress () {
            System.out.println(customAddress.getAddr());
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