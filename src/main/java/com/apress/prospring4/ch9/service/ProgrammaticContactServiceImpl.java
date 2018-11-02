package com.apress.prospring4.ch9.service;

import com.apress.prospring4.ch9.entities.Contact;
import com.apress.prospring4.ch9.repository.ContactRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("programmaticContactService")
@Repository
public class ProgrammaticContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;
    private TransactionTemplate transactionTemplate;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
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
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            public Long doInTransaction(TransactionStatus transactionStatus) {
                Query nativeQuery = em.createNativeQuery("Contact.countAll", Integer.class);
                return (Long) nativeQuery.getSingleResult();
            }
        });
    }
}
