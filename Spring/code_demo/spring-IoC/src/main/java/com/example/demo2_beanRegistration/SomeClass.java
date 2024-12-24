package com.example.demo2_beanRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/** (Registering Beans) @Component can be used on classes **/
@Component
public class SomeClass {

    /** (Registering Beans) @Bean can be used on methods **/
    @Bean
    public void someMethod() {}
}
