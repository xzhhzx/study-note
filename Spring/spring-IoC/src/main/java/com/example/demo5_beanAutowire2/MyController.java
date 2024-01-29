package com.example.demo5_beanAutowire2;

import com.example.demo5_beanAutowire2.service.MyService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Component
public class MyController {

    @Autowired
    Map<String, MyService> myServiceMap;

    @Autowired
    List<MyService> myServiceList;

    @Autowired
    Set<MyService> myServiceSet;
}
