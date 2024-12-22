package com.springboot.demo0;

import com.springboot.demo0.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Demo0Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo0Application.class, args);
//		Arrays.asList(context.getBeanDefinitionNames()).stream().sorted().forEach(s -> System.out.println(s));

        System.out.println(context.getBean("myService"));
        MyService myService = (MyService) context.getBean("myService");
        System.out.println(myService.getMyBean());
    }

}
