package com.apress.prospring4.ch5.pointcut.static_pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            public boolean matches(Class<?> aClass) {
                return aClass == BeanOne.class;
            }
        };
    }

    public boolean matches(Method method, Class<?> aClass) {
        return "foo".equals(method.getName());
    }
}
