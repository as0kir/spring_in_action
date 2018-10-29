package com.apress.prospring4.ch8;

import com.apress.prospring4.ch8.dto.ContactSummary;
import com.apress.prospring4.ch8.entities.ContactTelDetail;
import com.apress.prospring4.ch8.entities.Hobby;
import com.apress.prospring4.ch8.entities.Contact;
import com.apress.prospring4.ch8.service.ContactService;
import com.apress.prospring4.ch8.service.ContactSummaryService;
import com.apress.prospring4.ch8.service.ContactSummoryUntypeImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.*;

public class SpringJPASample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch8/app-context-xml.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("jpaContactService", ContactService.class);
        listContacts(contactService.findAll());

        listContactsWithDetail(contactService.findAllWithDetail());

        listContactsWithDetail(Arrays.asList(contactService.findById(1l)));

        ContactSummoryUntypeImpl contactSummoryUntype = ctx.getBean("contactSummaryUntype", ContactSummoryUntypeImpl.class);
        contactSummoryUntype.displayAllContactSummory();

        ContactSummaryService contactSummaryService = ctx.getBean("contactSummaryService", ContactSummaryService.class);
        for (ContactSummary contactSummary : contactSummaryService.findAll()) {
            System.out.println(contactSummary);
        }

        Contact contact = new Contact();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        contact.setBirthDate(new Date());

        ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "11111111");
        contact.addContactTelDetail(contactTelDetail);

        contactTelDetail = new ContactTelDetail("Mobile", "22222222");
        contact.addContactTelDetail(contactTelDetail);
        contactService.save(contact);

        listContactsWithDetail(contactService.findAllWithDetail());


        contact = contactService.findById(1l);
        System.out.println("");
        System.out.println(contact);
        System.out.println("");

        contact.setFirstName("Justin");
        Set<ContactTelDetail> contactTelDetails = contact.getContactTelDetails();
        ContactTelDetail toDeleteContactTel = null;
        for (ContactTelDetail telDetail : contactTelDetails) {
            if (telDetail.getTelType().equals("Home")){
                toDeleteContactTel = telDetail;
            }
        }
        contactTelDetails.remove(toDeleteContactTel);
        contactService.save(contact);
        listContactsWithDetail(contactService.findAllWithDetail());

        contact = contactService.findById(1l);
        contactService.delete(contact);
        listContactsWithDetail(contactService.findAllWithDetail());

        List<Contact> contacts = contactService.findByCriteriaQuery("John", "Smith");
        listContactsWithDetail(contacts);
    }

    private static void listContacts(List<Contact> contacts) {
        System.out.println("");
        System.out.println("Listing contacts without details: ");
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
