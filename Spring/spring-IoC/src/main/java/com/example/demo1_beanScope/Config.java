package com.example.demo1_beanScope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Company.class)
public class Config {

    /** Below is another (though ugly) way of defining the "company" bean. Uncommenting below can remove the @ComponentScan annotation **/
    // @Bean
    // @Scope("prototype")
    // public Company company() {
    //     return new Company(getAddress123());
    // }

    @Bean
//    @Scope("prototype") // Change here!
    public Address getAddress123() {
        return new Address("High Street", 1000);
    }
}