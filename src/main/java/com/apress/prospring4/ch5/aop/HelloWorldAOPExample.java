package com.apress.prospring4.ch5.aop;

import org.springframework.aop.framework.ProxyFactory;

public class HelloWorldAOPExample {
    public static void main(String[] args) {
        MessageWriter writer = new MessageWriter();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MessageDecorator());
        proxyFactory.setTarget(writer);

        MessageWriter proxy = (MessageWriter) proxyFactory.getProxy();

        writer.writeMessage();
        System.out.println(" ");
        proxy.writeMessage();

    }
}
