package com.apress.prospring4.ch8.service;

import com.apress.prospring4.ch8.entities.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
    List<Contact> findAllbyNativeQuery();
}
