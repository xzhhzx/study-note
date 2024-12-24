package com.springboot.demo1.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String error() {
        return "Error haven";
    }
}