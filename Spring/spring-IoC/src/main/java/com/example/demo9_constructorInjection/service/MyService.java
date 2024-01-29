package com.example.demo9_constructorInjection.service;

import com.example.demo9_constructorInjection.beans.MyBean;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    /*
    * Setting the field to final makes sense and is better, since the field content won’t ever change during the lifetime of an application.
    * It also helps to avoid programming errors, because the compiler will complain if we have forgotten to initialize the field.
    */
    @Getter
    private final MyBean myBean;

    /*
     * Using constructor injection, you can omit the @Autowired if the target bean defines only one constructor to begin with.
     * Also, myBean can be marked as final in this way, compared with field injection. Using field injection CANNOT achieve this feature.
     */
//    @Autowired
    public MyService(MyBean myBean) {
        this.myBean = myBean;
    }
}
