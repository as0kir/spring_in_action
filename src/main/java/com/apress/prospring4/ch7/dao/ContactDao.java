package com.apress.prospring4.ch7.dao;

import com.apress.prospring4.ch7.entities.Contact;
import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
}
