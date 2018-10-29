package com.apress.prospring4.ch8.service;

import com.apress.prospring4.ch8.dto.ContactSummary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactSummaryService")
@Repository
@Transactional
public class ContactSummaryServiceImpl implements ContactSummaryService {

    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    public List<ContactSummary> findAll() {
        List<ContactSummary> resultList = em.createQuery("select new com.apress.prospring4.ch8.dto.ContactSummary(c.firstName, c.lastName, t.telNumber) " +
                " from com.apress.prospring4.ch8.entities.Contact c left join c.contactTelDetails t where t.telType = 'Home'", ContactSummary.class).getResultList();
        return resultList;
    }
}
