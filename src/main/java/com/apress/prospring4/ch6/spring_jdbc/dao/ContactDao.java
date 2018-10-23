package com.apress.prospring4.ch6.spring_jdbc.dao;

import com.apress.prospring4.ch6.spring_jdbc.entities.Contact;

import java.util.List;

public interface ContactDao {
    String findFirstNameById(Long id);
    String findLastNameById(Long id);
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
}
