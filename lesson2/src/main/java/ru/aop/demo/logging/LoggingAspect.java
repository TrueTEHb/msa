package ru.aop.demo.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(ru.aop.demo.controller.GreetingController)")
    public void processingInputValue() {
    }

    @After("processingInputValue()")
    public void logProcessingInputValue(JoinPoint jp) {
        logger.info("Входное значение: " + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(value = "processingInputValue()", returning = "result")
    public void logProcessingOutputValue(JoinPoint jp, Object result) {
        logger.log(Level.INFO, "Возвращаемое значение: " + result.toString());
    }
}