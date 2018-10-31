package com.apress.prospring4.ch8.jpa.service;

import com.apress.prospring4.ch8.jpa.entities.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
    List<Contact> findAllbyNativeQuery();
    List<Contact> findByCriteriaQuery(String firstName, String lastName);

}
