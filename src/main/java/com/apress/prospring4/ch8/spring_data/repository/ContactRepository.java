package com.apress.prospring4.ch8.spring_data.repository;

import com.apress.prospring4.ch8.spring_data.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
//    List<Contact> findByFirstName(String firstName);
//    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
