package com.example.demo7_xmlAC;

import lombok.Data;

@Data
public class Address {
    public void setCity(String city) {
        this.city = city;
        System.out.println("=== Address.setCity(..) called");
    }

    // 1.<property>
    private String city;
    private String street;
    private int number;
}
