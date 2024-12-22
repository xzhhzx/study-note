package com.example.annotation_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

@Component
public class DemoClass {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void demoMethodWithoutArg() {
        logger.info("invoke demoMethodWithoutArg()");
    }

    public void demoMethodWith1Arg(String name) {
        logger.info("invoke demoMethodWith1Arg({})", name);
    }

    public void demoMethodWith2Args(String name, int age) {
        logger.info("invoke demoMethodWith2Args({}, {})", name, age);
    }

}
