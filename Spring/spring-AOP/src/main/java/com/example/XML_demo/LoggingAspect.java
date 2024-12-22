package com.example.XML_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void logAfterAdvice() {
        logger.info("==== execution DONE ====");
    }

    public void logBeforeAdvice() {
        logger.info("==== execution BEGIN ====");
    }

    public void logAfterReturnAdvice(Object returnValue) {
        logger.info("==== execution RETURN (with {}) ====", returnValue);
    }
}
