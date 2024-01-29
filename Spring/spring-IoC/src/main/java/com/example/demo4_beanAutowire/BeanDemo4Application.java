package com.example.demo4_beanAutowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDemo4Application {
    public static void main(String[] args) {
        String currentPackage = BeanDemo4Application.class.getPackage().getName();
        ApplicationContext context = new AnnotationConfigApplicationContext(currentPackage);
        // Below gets all defined Beans
        for(String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println("=======================================");
        /** If @Qualifier("someCollaboratorChild1"), then output 21;
         *  if @Qualifier("someCollaboratorChild2"), then output 42 */
        System.out.println(context.getBean("someClass", SomeClass.class).getSomeCollaborator().meaningOfLife());
        System.out.println(context.getBean("someClass", SomeClass.class).getAnotherCollaborator().meaningOfLife());
        System.out.println(context.getBean("someClass", SomeClass.class).someCollaboratorChild2.meaningOfLife());
        System.out.println("=======================================");
    }
}
