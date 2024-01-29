package com.example.demo7_xmlAC;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo7Application {
    public static void main(String[] args) {

        // Below creates an ApplicationContext from XML config file. Note:
        // 1.When creating bean `stu`, the container uses <constructor-arg> to initialize the bean, thus the constructor of Student will be called
        // 2.When creating bean `tch`, the container uses <property> to initialize the bean, thus the setters of Teacher will be called
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-demo7.xml");
        Student student = ctx.getBean("stu", Student.class);
        System.out.println(student);
        Teacher teacher = ctx.getBean("tch", Teacher.class);
        System.out.println(teacher);
    }
}
