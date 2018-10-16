package com.apress.prospring4.ch5.pointcut.proxy;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class NoOpBeforeAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] objects, Object o) throws Throwable {

    }
}
