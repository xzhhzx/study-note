package com.example.demo10_setterInjection;

import com.example.demo10_setterInjection.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo10Application {
    public static void main(String[] args) {
        String currentPackage = Demo10Application.class.getPackage().getName();
        ApplicationContext context = new AnnotationConfigApplicationContext(currentPackage);

        MyService myService = context.getBean("myService", MyService.class);
        System.out.println(myService.getMandatoryField());
        System.out.println(myService.getOptionalField());
    }
}
