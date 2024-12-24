package com.example.demo8_annotationAC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo8Application {
    public static void main(String[] args) {

        // M1: empty context. (need to manually call register() and refresh() )
        AnnotationConfigApplicationContext ctx1 = new AnnotationConfigApplicationContext();
        ctx1.register(MyConfiguration.class);
        ctx1.refresh();
        String answer1 = ctx1.getBean("getAnswerToTheUniverse", String.class);
        System.out.println(answer1);

        // M2 (recommended): scanning for components in the given packages, registering bean definitions for those components, and automatically refreshing the context.
        ApplicationContext ctx2 = new AnnotationConfigApplicationContext("com.example.demo8_annotationAC");
        String answer2 = ctx2.getBean("getAnswerToTheUniverse", String.class);
        System.out.println(answer2);

        // M3 (recommended): deriving bean definitions from the given component classes and automatically refreshing the context.
        ApplicationContext ctx3 = new AnnotationConfigApplicationContext(MyConfiguration.class);
        String answer3 = ctx3.getBean("getAnswerToTheUniverse", String.class);
        System.out.println(answer3);
    }
}
