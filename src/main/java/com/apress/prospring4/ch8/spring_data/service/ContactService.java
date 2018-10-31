package com.apress.prospring4.ch8.spring_data.service;

import com.apress.prospring4.ch8.spring_data.entities.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
