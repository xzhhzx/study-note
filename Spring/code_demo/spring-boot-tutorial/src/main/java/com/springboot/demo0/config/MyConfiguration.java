package com.springboot.demo0.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MyConfiguration {
    @Bean
    MyBean getMyBean() {
        return new MyBean();
    }
}
