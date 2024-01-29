package com.example.demo6_profile;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

public class Demo6Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		ConfigurableEnvironment env = context.getEnvironment();

		// Different profile will register different Beans. For "dev", devConfig will be registered; for "prod", prodConfig will be registered
		env.setActiveProfiles("dev");
//		env.setActiveProfiles("prod");
//		env.setActiveProfiles("dev", "prod");
		context.register(DevConfig.class, ProdConfig.class);
		context.refresh();

		// Print results
		for(String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		System.out.println("=== BEAN helloWorld: " + context.getBean("helloWorld"));
		System.out.println("=======================================");
		System.out.println(Arrays.toString(env.getActiveProfiles()));
		System.out.println(Arrays.toString(env.getDefaultProfiles()));
		System.out.println(env.getSystemEnvironment());
		System.out.println(env.getSystemProperties());
		System.out.println("=======================================");
	}
}

