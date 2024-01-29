package com.example.demo8_annotationAC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean
    String getAnswerToTheUniverse() {
        return "42";
    }
}
