package com.example.annotationDemo;

import java.lang.reflect.AnnotatedElement;

public class Main {
    public static void main(String[] args) {
        Class<Test> clazz = Test.class;
        processAnnotation(clazz);
    }

    public static void processAnnotation(Class<Test> elem) {
        System.out.println("===" + elem.getAnnotations().length);
        if (elem.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println("Annotation present!");
            MyAnnotation myAnnotation = elem.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.fieldName() + ", " + myAnnotation.fieldValue());
        }
    }
}
