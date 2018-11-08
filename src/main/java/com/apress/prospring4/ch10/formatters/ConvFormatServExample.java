package com.apress.prospring4.ch10.formatters;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch10/formatters/app-context-xml.xml");
        ctx.refresh();

        Contact chris = ctx.getBean("chris", Contact.class);
        System.out.println("Chris info: " + chris);

        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        System.out.println("Birthdate of contact is : " + conversionService.convert(chris.getBirthDate(), String.class));
    }
}
