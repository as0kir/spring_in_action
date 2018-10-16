package com.apress.prospring4.ch5.pointcut.proxy;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class TestPointcut extends StaticMethodMatcherPointcut {
    public boolean matches(Method method, Class<?> aClass) {
        return "advised".equals(method.getName());
    }
}
