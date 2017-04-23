package com.kugiojotaro.placesshots.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
public class LoggingAspect {

    @Before("execution(* com.kugiojotaro.placesshots.repository.impl.*.*(..))")
    public void beforeExecution(JoinPoint joinPoint) {
    	log.info(joinPoint.getTarget().getClass().getName() + " - " + joinPoint.getSignature().getName() + "(" + Arrays.toString(joinPoint.getArgs()) + ")");
    }

//    @AfterReturning(pointcut="execution(* com.kugiojotaro.placesshots.repository.impl.*.*(..))", returning="result")
//    public void afterExecution(Object result) {
//    	log.info(" return " + result + ")");
//    }

}