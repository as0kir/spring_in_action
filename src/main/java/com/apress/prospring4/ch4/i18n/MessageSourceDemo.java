package com.apress.prospring4.ch4.i18n;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class MessageSourceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:/META-INF/spring/ch4/i18n/app-context-i18n-xml.xml");
        ctx.refresh();

        Locale english = Locale.ENGLISH;
        Locale russian = new Locale("ru", "RU");

        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, russian));

        System.out.println(ctx.getMessage("nameMsg", new Object[]{"Alex", "Kirillov"}, english));
    }
}
