package com.apress.prospring4.ch9;

import com.apress.prospring4.ch9.entities.Contact;
import com.apress.prospring4.ch9.service.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class TxDeclarativeSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch9/app-declarative-context-xml.xml");
        ctx.refresh();

        ContactService contactService = (ContactService) ctx.getBean("declarativeContactService");
        List<Contact> contacts = contactService.findAll();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }

        Contact contact = contactService.findById(1l);
        contact.setFirstName("Peter");
        contactService.save(contact);
        System.out.println("Contact saved successfully: " + contact);
        System.out.println("Contact count: " + contactService.countAll());
    }
}
