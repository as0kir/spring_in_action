package com.apress.prospring4.ch10.properties;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PropEditorExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch10/properties/app-context-xml.xml");
        ctx.refresh();

        Contact chris = ctx.getBean("chris", Contact.class);
        System.out.println("Chris info: " + chris);

        Contact myContact = ctx.getBean("myContact", Contact.class);
        System.out.println("My contact info: " + myContact);

    }
}
