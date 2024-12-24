package com.example.demo3_beanNaming;

import org.springframework.stereotype.Component;

@Component
public class AaaClass {
    // NOTE: cannot define a Bean with the same name as another already-registered Bean,
    // except that overriding configuration is set to allowed
//    @Bean
//    public void aMethod() {}
}
