package com.example.demo1_beanScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.BeanDemo");
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        // Below gets all defined Beans
        for(String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        // 1.If scope of Company and Address is "singleton", below will create 1 Company object and 1 Address object
        // 2.If scope of Company is "prototype", below will create 2 Company objects but 1 Address object (share the same Address)
        // 3.If scope of Company and Address is "prototype", below will create 2 Company objects and 2 Address objects
        Company company1 = context.getBean("company", Company.class);
        Company company2 = context.getBean("company", Company.class);
        System.out.println("company1 == company2? : " + (company1 == company2));
        System.out.println("company1.address == company2.address? : " + (company1.getAddress() == company2.getAddress()));
        System.out.println(company1.toString() + " : " + company1.getAddress());
        System.out.println(company2.toString() + " : " + company2.getAddress());
        System.out.println(company1.getAddress().getStreet());
    }
}
