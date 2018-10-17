package com.apress.prospring4.ch5.proxy_factory_bean;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MyAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("executing " + method.getName());
    }
}
