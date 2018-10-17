package com.apress.prospring4.ch5.proxy_factory_bean_aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint, int intValue){
        if(intValue != 100)
            System.out.println("executing: " +
                    joinPoint.getSignature().getDeclaringTypeName() + " " +
                    joinPoint.getSignature().getName() + " " +
                    " argument: " + intValue);
    }

    public Object simpleAroundAdvice(ProceedingJoinPoint pjp, int intValue) throws Throwable {
        System.out.println("Before execute: " +
                pjp.getSignature().getDeclaringTypeName() + " " +
                pjp.getSignature().getName() + " " +
                " argument: " + intValue);

        Object retObject = pjp.proceed();

        System.out.println("After execute: " +
                pjp.getSignature().getDeclaringTypeName() + " " +
                pjp.getSignature().getName() + " " +
                " argument: " + intValue);
        return retObject;
    }

}
