package com.apress.prospring4.ch9.service;

import com.apress.prospring4.ch9.entities.Contact;
import com.apress.prospring4.ch9.repository.ContactRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("declarativeContactService")
@Repository
public class DeclarativeContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public long countAll() {
        return contactRepository.countAllContacts();
    }
}
