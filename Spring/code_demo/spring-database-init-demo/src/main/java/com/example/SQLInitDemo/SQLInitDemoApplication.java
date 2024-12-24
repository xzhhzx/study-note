package com.example.SQLInitDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.SQLInitDemo.mapper"})
public class SQLInitDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SQLInitDemoApplication.class, args);
	}

}
