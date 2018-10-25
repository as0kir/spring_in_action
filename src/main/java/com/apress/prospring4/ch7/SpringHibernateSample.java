package com.apress.prospring4.ch7;

import com.apress.prospring4.ch7.dao.ContactDao;
import com.apress.prospring4.ch7.entities.Contact;
import com.apress.prospring4.ch7.entities.ContactTelDetail;
import com.apress.prospring4.ch7.entities.Hobby;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpringHibernateSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch7/app-context-xml.xml");
        ctx.refresh();

        ContactDao contactDao = (ContactDao) ctx.getBean("contactDao");
        listContacts(contactDao.findAllWithDetail());

        System.out.println("------------------------------------------------------");
        Contact contact = contactDao.findById(1l);
        System.out.println();
        System.out.println(contact);
        System.out.println();

        System.out.println("------------------------------------------------------");
        contact = new Contact();
        contact.setFirstName("Michel");
        contact.setLastName("Jackson");
        contact.setBirthDate(new Date());

        ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "11111111");
        contact.addContactTelDetail(contactTelDetail);
        contactTelDetail = new ContactTelDetail("Mobile", "22222222");
        contact.addContactTelDetail(contactTelDetail);

        contactDao.save(contact);

        listContacts(contactDao.findAllWithDetail());

        System.out.println("------------------------------------------------------");
        contact = contactDao.findById(1l);
        contact.setFirstName("Kim Fung");

        Set<ContactTelDetail> contactTelDetailSet = contact.getContactTelDetails();
        ContactTelDetail toDeleteContactTel = null;
        for (ContactTelDetail telDetail : contactTelDetailSet) {
            if(telDetail.getTelType().equals("Home")) {
                toDeleteContactTel = telDetail;
            }
        }
        contact.removeContactTelDetail(toDeleteContactTel);
        contactDao.save(contact);

        listContacts(contactDao.findAllWithDetail());

        System.out.println("------------------------------------------------------");
        contact = contactDao.findById(1l);

        contactDao.delete(contact);
        listContacts(contactDao.findAllWithDetail());
    }

    private static void listContacts(List<Contact> contacts) {
        System.out.println();
        System.out.println("Listing contacts without details:");
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
