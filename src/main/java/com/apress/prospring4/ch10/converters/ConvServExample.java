package com.apress.prospring4.ch10.converters;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvServExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch10/converters/app-context-xml.xml");
        ctx.refresh();

        Contact chris = ctx.getBean("chris", Contact.class);
        System.out.println("Chris info: " + chris);

        ConversionService conversionService = ctx.getBean(ConversionService.class);
        AnotherContact anotherContact = conversionService.convert(chris, AnotherContact.class);
        System.out.println("Another contact info: " + anotherContact);

        String[] strings = conversionService.convert("a,b,c", String[].class);
        System.out.println("String array: " + strings[0] + strings[1] + strings[2]);

        List<String> stringList = new ArrayList<String>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        Set<String> hashSet = conversionService.convert(stringList, HashSet.class);

        for (String s : hashSet) {
            System.out.println("set: " + s);
        }


    }
}
