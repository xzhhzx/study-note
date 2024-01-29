package com.example.demo10_beanByType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class DependencyBean {
    @Bean
    public String aRandomBeanName() {
        return "hallo";
    }
}
