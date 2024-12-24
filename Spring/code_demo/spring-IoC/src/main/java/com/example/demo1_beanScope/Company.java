package com.example.demo1_beanScope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // Change here!
public class Company {

    private Address address;

    /** Side note: the console displays: Autowiring by type from bean name 'company' via constructor to bean named 'getAddress123' **/
    /** (This constructor is mandatory for autowiring the required bean) **/
    public Company(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}