package com.example.demo10_beanInjectByType;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo10Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.demo10_beanInjectByType");

        for (String bean : context.getBeanDefinitionNames()) {
            System.out.println(bean);
        }

        String a = context.getBean("doubleGreeting", String.class);
        System.out.println("doubleGreeting: " + a);
    }
}
