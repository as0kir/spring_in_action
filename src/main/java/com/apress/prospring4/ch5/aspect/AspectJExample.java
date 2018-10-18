package com.apress.prospring4.ch5.aspect;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =  new GenericXmlApplicationContext();
        ctx.load(new String[]{"classpath:META-INF/spring/ch5/aspect/app-context-xml.xml"});
        ctx.refresh();
        MessageWriter writer = new MessageWriter();
        writer.writeMessage();
        writer.foo();
    }
}
