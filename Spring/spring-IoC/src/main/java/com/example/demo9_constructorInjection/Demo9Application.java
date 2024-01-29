package com.example.demo9_constructorInjection;

import com.example.demo9_constructorInjection.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo9Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.demo9_constructorInjection");

        System.out.println(context.getBean("myService"));
        MyService myService = context.getBean("myService", MyService.class);
        System.out.println(myService.getMyBean());
    }
}
