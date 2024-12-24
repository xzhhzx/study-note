package com.springboot.demo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

    @RequestMapping("/long-text")
    public String longText() {
        String a = "";
        for (int i=0; i<1000; i++) {
            a += "hello" + i + "\n";
        }
        return a;
    }
}
