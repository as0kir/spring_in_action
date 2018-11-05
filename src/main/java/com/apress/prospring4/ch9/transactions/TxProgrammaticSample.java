package com.apress.prospring4.ch9.transactions;

import com.apress.prospring4.ch9.transactions.service.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TxProgrammaticSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch9/app-programmatic-context-xml.xml");
        ctx.refresh();

        ContactService contactService = (ContactService) ctx.getBean("programmaticContactService");
        System.out.println("Contact count: " + contactService.countAll());
    }
}
