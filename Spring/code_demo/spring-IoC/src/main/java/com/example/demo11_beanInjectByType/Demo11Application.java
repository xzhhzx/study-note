package com.example.demo11_beanInjectByType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo11Application {
    public static void main(String[] args) {
        String currentPackage = Demo11Application.class.getPackage().getName();
        ApplicationContext context = new AnnotationConfigApplicationContext(currentPackage);

        for (String bean : context.getBeanDefinitionNames()) {
            System.out.println(bean);
        }

        System.out.println("===========================");
        String a = context.getBean("doubleGreeting", String.class);
        System.out.println("doubleGreeting: " + a);
    }
}
