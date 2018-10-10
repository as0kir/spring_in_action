package com.apress.prospring4.ch4.environment;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PlaceHolderSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch4/environment/app-context-xml.xml");
        ctx.refresh();

        AppProperty property = ctx.getBean("appProperty", AppProperty.class);
        System.out.println("application.home: " + property.getApplicationHome());
        System.out.println("user.home: " + property.getUserHome());
    }
}
