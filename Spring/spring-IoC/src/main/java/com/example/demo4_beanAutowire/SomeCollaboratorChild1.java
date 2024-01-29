package com.example.demo4_beanAutowire;


import org.springframework.stereotype.Component;

@Component
public class SomeCollaboratorChild1 extends SomeCollaborator {
    @Override
    public int meaningOfLife() {
        return 21;
    }
}
