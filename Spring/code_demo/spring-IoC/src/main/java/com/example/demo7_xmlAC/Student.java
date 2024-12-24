package com.example.demo7_xmlAC;

import lombok.Data;

@Data
public class Student {
    // 2.<constructor-arg> with value
    private String id;
    // 3.<constructor-arg> with value (name omitted)
    private String name;
    // 4.<constructor-arg> with ref (autowired by constructor injection)
    private Address address;

    public Student(String id, String name, Address address) {
        System.out.println("=== Constructor of Student called");
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
