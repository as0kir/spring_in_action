package com.apress.prospring4.ch6.spring_jdbc;

import com.apress.prospring4.ch6.spring_jdbc.dao.ContactDao;
import com.apress.prospring4.ch6.spring_jdbc.entities.Contact;
import com.apress.prospring4.ch6.spring_jdbc.entities.ContactTelDetail;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class JdbcContactDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch6/spring_jdbc/app-context-xml.xml");
        ctx.refresh();

        ContactDao contactDao = (ContactDao) ctx.getBean("contactDao");
        System.out.println("First name for contact id 1 is " + contactDao.findFirstNameById(1l));
        System.out.println("Last name for contact id 1 is " + contactDao.findLastNameById(1l));
        for (Contact contact : contactDao.findAll()) {
            System.out.println(contact);
            if (contact.getContactTelDetails()!=null){
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println("---"+contactTelDetail);
                }

            }
            System.out.println("");
        }

        for (Contact contact : contactDao.findAllWithDetail()) {
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
