package com.example.demo4_beanAutowire;


import org.springframework.stereotype.Component;

@Component
public class SomeCollaboratorChild2 extends SomeCollaborator {
    @Override
    public int meaningOfLife() {
        return 42;
    }
}
