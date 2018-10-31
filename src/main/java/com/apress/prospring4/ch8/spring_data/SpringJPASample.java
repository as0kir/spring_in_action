package com.apress.prospring4.ch8.spring_data;

import com.apress.prospring4.ch8.spring_data.entities.Contact;
import com.apress.prospring4.ch8.spring_data.entities.ContactTelDetail;
import com.apress.prospring4.ch8.spring_data.entities.Hobby;
import com.apress.prospring4.ch8.spring_data.service.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringJPASample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch8/spring_data/app-context-xml.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("springJpaContactService", ContactService.class);
        listContacts("Find all:", contactService.findAll());
    }

    private static void listContacts(String message, List<Contact> contacts) {
        System.out.println("");
        System.out.println(message);
        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }

    private static void listContactsWithDetail(List<Contact> contacts) {
        System.out.println("");
        System.out.println("Listing contacts without details: ");
        for (Contact contact : contacts) {
            System.out.println(contact);

            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println(contactTelDetail);
                }
            }
            if (contact.getHobbies() != null) {
                for (Hobby hobby : contact.getHobbies()) {
                    System.out.println(hobby);
                }
            }

            System.out.println();
        }
    }
}
