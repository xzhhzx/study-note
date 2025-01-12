package com.example.demo12_manualAC;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo12Application {
    public static void main(String[] args) {
        String currentPackage = Demo12Application.class.getPackage().getName();
        ApplicationContext context = new AnnotationConfigApplicationContext(currentPackage);


        // When is @PropertySource loaded?
//        ac.getEnvironment()
    }
}
