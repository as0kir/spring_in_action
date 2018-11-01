package com.apress.prospring4.ch8.spring_data;

import com.apress.prospring4.ch8.spring_data.entities.ContactAudit;
import com.apress.prospring4.ch8.spring_data.service.ContactAuditService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class SpringAuditJPASample {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch8/spring_data/app-context-xml.xml");
        ctx.refresh();

        ContactAuditService contactAuditService = ctx.getBean("contactAuditService", ContactAuditService.class);

        List<ContactAudit> contactAudits = contactAuditService.findAll();
        listContacts(contactAudits);

        System.out.println("Add new contact");
        ContactAudit contactAudit = new ContactAudit();
        contactAudit.setFirstName("Michael");
        contactAudit.setLastName("Jackson");
        contactAudit.setBirthDate(new Date());
        contactAuditService.save(contactAudit);

        contactAudits = contactAuditService.findAll();
        listContacts(contactAudits);

        contactAudit = contactAuditService.findById(1l);
        System.out.println("");
        System.out.println("Contact with id 1:" + contactAudit);
        System.out.println();

        System.out.println("Update contact");
        contactAudit.setFirstName("Tom");
        contactAuditService.save(contactAudit);

        contactAudits = contactAuditService.findAll();
        listContacts(contactAudits);

        contactAudit = contactAuditService.findAuditByRevision(1l, 1);
        System.out.println("");
        System.out.println("Old Contact with id 1 and rev 1: " + contactAudit);
        System.out.println();

        contactAudit = contactAuditService.findAuditByRevision(1l, 2);
        System.out.println("");
        System.out.println("Old Contact with id 1 and rev 2: " + contactAudit);
        System.out.println();

    }

    private static void listContacts(List<ContactAudit> contacts) {
        System.out.println("");
        System.out.println("List contacts without details: ");
        for (ContactAudit contact : contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }
}
