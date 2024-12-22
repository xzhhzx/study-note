package com.example.annotation_demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DemoAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Pointcut: public methods within package com.example.annotation_demo, with at least one argument
    @Pointcut("execution(public * *(..)) && within(com.example.annotation_demo.*) && args(val,..)")
    private void aNamedPointcut(Object val) {}

    // Advice (before): get join point context, get first parameter val
    // (Note: the join point context can be omitted from parameters)
    @Before("aNamedPointcut(val)")
    public void doPreLogging(JoinPoint jp, Object val) {
        logger.info("(before join point [{}])", jp.toString());
        logger.info("(get first parameter [{}])", val.toString());
    }

    // Advice (around): do timing for method executions
    @Around("execution(public * *(..)) && within(com.example.annotation_demo.*)")
    public Object doProfiling(ProceedingJoinPoint pjp) {
        logger.info("--- START execution of method {}", pjp.getSignature().toShortString());
        long start = System.nanoTime();
        Object ret = null;
        try {
            ret = pjp.proceed();    // Invocation of the actual method
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long end = System.nanoTime();
        logger.info("--- END execution, took [{}] milliseconds", (end-start) / 1000 / 1000);
        return ret;
    }


    /* Below is very wierd. This will make the embedded Tomcat unable to start. */
//    @Before("execution(* *app*(..)) and this(obj)")
//    public void beforeAdvice(Object obj) {
//        System.out.println("===== Invoked from: " + obj.toString());
//    }
}
