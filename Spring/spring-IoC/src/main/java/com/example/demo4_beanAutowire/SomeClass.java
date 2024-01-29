package com.example.demo4_beanAutowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SomeClass {

    /** Designating collaborators with `@Autowired` (can be used on properties, setters, and constructors) **/
    /** Specifying collaborator implementation with `@Qualifier` **/

    // 1.This is autowired as `someCollaboratorChild1` (by field injection)
    @Autowired
    @Qualifier("someCollaboratorChild1")
    SomeCollaborator someCollaborator;

    // 2.This is autowired as `someCollaboratorChild2` (by constructor injection)
    // (NOTE: this field can be marked as `final` since it is a parameter of constructor. See more in demo 9)
    final SomeCollaborator anotherCollaborator;

    // 3.This is autowired as `someCollaboratorChild2` (follow attribute naming convention)
    @Autowired
    SomeCollaborator someCollaboratorChild2;

    // 2.Constructor injection (NOTE: the `@Autowire` can be omitted)
    @Autowired
    public SomeClass(@Qualifier("someCollaboratorChild2") SomeCollaborator someCollaborator) {
        this.anotherCollaborator = someCollaborator;
    }

    // Helper get methods
    public SomeCollaborator getSomeCollaborator() {
        return someCollaborator;
    }
    public SomeCollaborator getAnotherCollaborator() {
        return anotherCollaborator;
    }
}
