package com.apress.prospring4.ch4.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Publisher implements ApplicationContextAware{
    private ApplicationContext ctx;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void publish(String message){
        ctx.publishEvent(new MessageEvent(this, message));
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/ch4/event/app-context-event-xml.xml");
        Publisher pub = (Publisher) ctx.getBean("publicher");
        pub.publish("Hello world!");
        pub.publish("!!!!!");
    }
}
