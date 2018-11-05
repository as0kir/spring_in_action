package com.apress.prospring4.ch9.transactions.service;


import com.apress.prospring4.ch9.transactions.entities.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
    long countAll();
}
