package com.example.demo3_beanNaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BeanDemo3Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BeanDemo3Application.class, args);
		System.out.println("=======================================");
		System.out.println(context.containsBean("aClass")); // false
		System.out.println(context.containsBean("AClass")); // true
		System.out.println(context.containsBean("aaaClass")); // true
		System.out.println(context.containsBean("AaaClass")); // false
		System.out.println("=======================================");
	}

}
