package com.apress.prospring4.ch8.service;

import com.apress.prospring4.ch8.entities.Contact;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    private static final String ALL_CONTACT_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from contact";

    private Log log = LogFactory.getLog(ContactServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    public List<Contact> findAll() {
        List<Contact> contacts = em.createNamedQuery("Contact.findAll", Contact.class).getResultList();
        return contacts;
    }

    public List<Contact> findAllWithDetail() {
        List<Contact> contacts = em.createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();
        return contacts;
    }

    public Contact findById(Long id) {
        TypedQuery<Contact> query = em.createNamedQuery("Contact.findById", Contact.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Contact save(Contact contact) {
        if(contact.getId() == null) {
            log.info("Inserting new contact");
            em.persist(contact);
        }
        else {
            em.merge(contact);
            log.info("Updating new contact");
        }
        log.info("Contact saved with id: " + contact.getId());
        return contact;
    }

    public void delete(Contact contact) {
        Contact mergedContact = em.merge(contact);
        em.remove(mergedContact);
        log.info("Contact with id: " + contact.getId() + " deleted successfully");
    }

    @Transactional(readOnly = true)
    public List<Contact> findAllbyNativeQuery() {
        //return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, Contact.class).getResultList();
        return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, "contactResult").getResultList();
    }
}
