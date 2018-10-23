package com.apress.prospring4.ch6.annotation_jdbc;

import com.apress.prospring4.ch6.annotation_jdbc.dao.ContactDao;
import com.apress.prospring4.ch6.annotation_jdbc.entities.Contact;
import com.apress.prospring4.ch6.annotation_jdbc.entities.ContactTelDetail;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotationJdbcDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch6/annotation_jdbc/app-context-xml.xml");
        ctx.refresh();

        ContactDao contactDao = (ContactDao) ctx.getBean("contactDao");
        for (Contact contact : contactDao.findAll()) {
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
