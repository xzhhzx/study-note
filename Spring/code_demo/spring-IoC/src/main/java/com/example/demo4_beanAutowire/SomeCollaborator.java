package com.example.demo4_beanAutowire;


import org.springframework.stereotype.Component;

@Component
public abstract class SomeCollaborator {
//    public int number = 0; // haha, data attributes are not polymorphic in Java.
    public int meaningOfLife() {
        return 0;
    }
}
