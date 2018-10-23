package com.apress.prospring4.ch6.jdbc.dao;

import com.apress.prospring4.ch6.jdbc.entities.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Contact contact);
    void update(Contact contact);
    void delete(Long idContact);
}
