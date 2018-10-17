package com.apress.prospring4.ch5.proxy_factory_bean_aop;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopNamespaceExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch5/proxy_factory_bean_aop/app-context-xml.xml");
        ctx.refresh();

        MyBean myBean = (MyBean) ctx.getBean("myBean");
        myBean.execute();
    }
}
