package com.apress.prospring4.ch6.annotation_jdbc;

import com.apress.prospring4.ch6.annotation_jdbc.dao.ContactDao;
import com.apress.prospring4.ch6.annotation_jdbc.entities.Contact;
import com.apress.prospring4.ch6.annotation_jdbc.entities.ContactTelDetail;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class AnnotationJdbcDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch6/annotation_jdbc/app-context-xml.xml");
        ctx.refresh();

        ContactDao contactDao = (ContactDao) ctx.getBean("contactDao");

        listContacts(contactDao.findAll());

        System.out.println("---------------------------------------------------------------------");
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setFirstName("Chris");
        contact.setLastName("John");
        contact.setBirthDate(new Date(new GregorianCalendar(1977, 10, 1).getTime().getTime()));
        contactDao.update(contact);

        listContacts(contactDao.findAll());

        System.out.println("---------------------------------------------------------------------");
        contact = new Contact();
        contact.setFirstName("Rod");
        contact.setLastName("Jonson");
        contact.setBirthDate(new Date(new GregorianCalendar(2001, 10, 1).getTime().getTime()));
        contactDao.insert(contact);

        listContacts(contactDao.findAll());

        System.out.println("---------------------------------------------------------------------");
        listContacts(contactDao.findByFirstName("Chris"));


        System.out.println("---------------------------------------------------------------------");
        contact =  new Contact();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        contact.setBirthDate(new Date((new GregorianCalendar(1964, 10,  1)).getTime().getTime()));

        List<ContactTelDetail> contactTelDetails = new ArrayList<ContactTelDetail>();
        ContactTelDetail contactTelDetail = new  ContactTelDetail();
        contactTelDetail.setTelType("Home");
        contactTelDetail.setTelNumber("11111111");
        contactTelDetails.add(contactTelDetail);

        contactTelDetail = new ContactTelDetail();
        contactTelDetail.setTelType("MoЬile");
        contactTelDetail.setTelNumber("22222222");
        contactTelDetails.add(contactTelDetail);
        contact.setContactTelDetails(contactTelDetails);

        contactDao.insertWithDetail(contact);

        listContacts(contactDao.findAllWithDetail());
    }

    private static void listContacts(List<Contact> list){
        for (Contact contact : list) {
            System.out.println(contact);
            if (contact.getContactTelDetails()!=null){
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println("---"+contactTelDetail);
                }

            }
            System.out.println("");
        }
    }
}
