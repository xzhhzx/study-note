package com.example.demo11_manualAC;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.StandardEnvironment;

public class Demo10Application {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext("com.example.demo10_beanInjectByType");

        // When is @PropertySource loaded?
//        ac.getEnvironment()
    }
}
