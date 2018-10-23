package com.apress.prospring4.ch6.jdbc;

import com.apress.prospring4.ch6.jdbc.entities.Contact;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PlainJdbsSample {
    private static com.apress.prospring4.ch6.jdbc.dao.ContactDao contactDao = new com.apress.prospring4.ch6.jdbc.dao.PlainContactDao();

    public static void main(String[] args) {
        //  Вывести начальный список контактов
        System.out.println("Listing initial contact data:");
        listAllContacts();

        System.out.println();
        //  Вставить новый контакт
        System.out.println("Insert a new  contact");
        Contact contact = new Contact();
        contact.setFirstName("Jacky");
        contact.setLastName("Chan");
        contact.setBirthDate(new Date((new GregorianCalendar(2001, 10, 1)).getTime().getTime()));
        contactDao.insert(contact);

        //  Вывести список контактов после создания нового контакта
        System.out.println("Listing contact data after new contact created:");
        listAllContacts();
        System.out.println();

        //  Удалить только что созданный контакт
        System.out.println("Deleting the previous created contact");
        contactDao.delete(contact.getId());
        // Вывести список контактов после удаления ранее созданного контакта
        System.out.println("Listing contact data after new contact deleted:");
        listAllContacts();
    }

    private static void listAllContacts() {
        List<Contact> contacts = contactDao.findAll();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
