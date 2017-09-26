package org.fsdcic.xjh.mongoClient.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by ben on 2017-9-26.
 */

@Component
@Aspect
public class Operator {

    @Pointcut("execution(* org.fsdcic.xjh.mongo.service..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("AOP Before Advice...");
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("AOP After Advice...");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint, Object returnVal) {
        System.out.println("AOP AfterReturning Advice:" + returnVal);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("AOP AfterThrowing Advice..." + error);
        System.out.println("AfterThrowing...");
    }

//   执行前后都会调用，但如果与before 和 after 同时使用，会使监听方法的返回值为null
//    @Around("pointCut()")
//    public void around(ProceedingJoinPoint pjp) {
//        System.out.println("AOP Aronud before...");
//        try {
//            pjp.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        System.out.println("AOP Aronud after...");
//    }

}

