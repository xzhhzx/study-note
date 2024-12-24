package com.springboot.demo1;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Demo1Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo1Application.class, args);
    }

}
