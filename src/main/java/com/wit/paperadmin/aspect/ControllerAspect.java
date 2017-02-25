package com.wit.paperadmin.aspect;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 何鹏帅 on 2016/11/24.
 */

public class ControllerAspect {
    Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    public void beforeMethod(JoinPoint joinpoint) {
        String methodName = joinpoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinpoint.getArgs());
        logger.info( methodName +": " + args);
    }


    public void afterMethod(JoinPoint joinpoint) {
        String methodName = joinpoint.getSignature().getName();
        System.out.println("The method "+ methodName +" ends");
    }

    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method "+ methodName +" ends with " + result);
    }


    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method "+ methodName +" occurs with exception:" + ex);
    }

}
