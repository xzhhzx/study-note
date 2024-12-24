package com.example.demo10_beanInjectByType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyBean {
    @Bean
    public String aRandomBeanName() {
        return "hallo";
    }
}
