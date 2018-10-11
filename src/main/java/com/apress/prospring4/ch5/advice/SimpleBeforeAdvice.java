package com.apress.prospring4.ch5.advice;

import com.apress.prospring4.ch5.aop.MessageWriter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice{
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before method: " + method.getName());
    }

    public static void main(String[] args) {
        MessageWriter writer = new MessageWriter();
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new SimpleBeforeAdvice());
        factory.setTarget(writer);

        MessageWriter proxy = (MessageWriter) factory.getProxy();
        proxy.writeMessage();
    }
}
