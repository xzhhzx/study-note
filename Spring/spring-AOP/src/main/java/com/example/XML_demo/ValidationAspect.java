package com.example.XML_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void validateSetValueBeforeAdvice(Object passedInValue) throws Throwable {
        if (passedInValue == null) {
            logger.error("Passed in value for set method cannot be null!");
        } else {
            logger.info("==== set value to: {} ====", passedInValue);
        }
    }
}
