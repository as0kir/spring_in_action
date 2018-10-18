package com.apress.prospring4.ch5.aspectj_annotation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJAnnotationExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch5/aspectj_annotation/app-context-xml.xml");
        ctx.refresh();

        MyBean myBean = (MyBean) ctx.getBean("myBean");
        myBean.execute();
    }
}
