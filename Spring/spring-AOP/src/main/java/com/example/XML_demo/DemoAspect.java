package com.example.XML_demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void testAdvice0(Object obj) {
        logger.info("in testAdvice0, get object: {}", obj.toString());
    }

    public void testAdvice1(Exception ex) {
        logger.info("in testAdvice1: {}", ex.getMessage());
    }

    public Object testAdvice2(ProceedingJoinPoint pjp) throws Throwable {
        // Here do something before
        logger.info("around (before)");
        Object obj = pjp.proceed();
        // Here do something after
        logger.info("around (after)");
        return obj;
    }
}
