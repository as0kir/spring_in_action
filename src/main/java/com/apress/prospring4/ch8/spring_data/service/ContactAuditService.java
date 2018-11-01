package com.apress.prospring4.ch8.spring_data.service;

import com.apress.prospring4.ch8.spring_data.entities.ContactAudit;

import java.util.List;

public interface ContactAuditService {
    List<ContactAudit> findAll();
    ContactAudit findById(Long id);
    ContactAudit save(ContactAudit contact);
    ContactAudit findAuditByRevision(Long id, int revision);
}
