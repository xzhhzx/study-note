package com.example.demo2_beanRegistration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.BeanDemo2");
        // Below gets all defined Beans
        for(String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println("=======================================");
        System.out.println(context.containsBean("beanDemo2Application"));	// "true" due to @SpringBootApplication
        System.out.println(context.containsBean("someClass"));	// "true" due to @Component
        System.out.println(context.containsBean("someMethod")); // "true" due to @Bean
        System.out.println("=======================================");
    }
}
