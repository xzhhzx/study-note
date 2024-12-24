package com.example.demo7_xmlAC;

import lombok.ToString;

//@Data
@ToString
//@AllArgsConstructor
// If all args constructor is defined, then DI must use constructor-based injection
// Here we want to test setter-based injection, thus comment this out
public class Teacher {
    // 5.<property> with value
    private String id;
    private String name;
    // 6.<property> with ref (autowired by constructor injection)
    private Address address;

    public void setId(String id) {
        System.out.println("=== Teacher.setId(..) called");
        this.id = id;
    }

    public void setName(String name) {
        System.out.println("=== Teacher.setName(..) called");
        this.name = name;
    }

    public void setAddress(Address address) {
        System.out.println("=== Teacher.setAddress(..) called");
        this.address = address;
    }

    // TODO: use AOP to test setter-based injection really invokes the setters
}
