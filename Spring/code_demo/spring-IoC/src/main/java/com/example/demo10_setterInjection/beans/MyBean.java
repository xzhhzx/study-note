package com.example.demo10_setterInjection.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBean {

    @Bean
    public String mandatoryField() {
        return "MANDATORY";
    }

    @Bean
    public String optionalField() {
        return "OPTIONAL";
    }
}
