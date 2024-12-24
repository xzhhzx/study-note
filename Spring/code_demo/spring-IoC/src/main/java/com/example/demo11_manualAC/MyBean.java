package com.example.demo11_manualAC;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    @Bean
    // Spring basics: the parameter of the method is auto-injected BY TYPE
    // 为什么要按类型注入，而不是名称呢？因为Spring默认按照单例模式管理bean，一个类型对应一个对象。
    // 所以这里注入了 String 类型的参数，无论Spring container里的bean叫什么名字都无所谓
    // 因此，方法参数不必声明为aRandomBeanName，而是任意名字都行
    public String doubleGreeting(String greeting) {
        String res = greeting + " " + greeting;
        return res;
    }

    // If injection by name is preferred, @Qualifier may be used to inject dependency BY NAME
    // See: https://www.logicbig.com/tutorials/spring-framework/spring-core/javaconfig-methods-inter-dependency.html
}
