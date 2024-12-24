package com.example.demo5_beanAutowire2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.TreeMap;

@SpringBootApplication
public class BeanDemo5Application {

	public static void main(String[] args) {
		String currentPackage = BeanDemo5Application.class.getPackage().getName();
		ApplicationContext context = new AnnotationConfigApplicationContext(currentPackage);
		System.out.println("=======================================");
		MyController obj = (MyController) context.getBean("myController");
		System.out.println(obj.getMyServiceMap());
		System.out.println(obj.getMyServiceMap() instanceof TreeMap);
		System.out.println(obj.getMyServiceMap() instanceof HashMap);
		System.out.println(obj.getMyServiceList());
		System.out.println(obj.getMyServiceSet());
		System.out.println("=======================================");
	}
}
