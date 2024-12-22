package com.example.annotation_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;
import java.util.StringTokenizer;

@SpringBootApplication
public class AnnotationDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(AnnotationDemoApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AnnotationDemoApplication.class, args);
		DemoClass demoClass = ctx.getBean("demoClass", DemoClass.class);
		logger.info("================== EXAMPLE 1 ==================");
		demoClass.demoMethodWithoutArg();
		logger.info("----------------------------");
		demoClass.demoMethodWith1Arg("zihan");
		logger.info("----------------------------");
		demoClass.demoMethodWith2Args("xiaoyun", 22);
	}
}
