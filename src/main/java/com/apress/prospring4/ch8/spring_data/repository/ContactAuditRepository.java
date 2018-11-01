package com.apress.prospring4.ch8.spring_data.repository;

import com.apress.prospring4.ch8.spring_data.entities.ContactAudit;
import org.springframework.data.repository.CrudRepository;

public interface ContactAuditRepository extends CrudRepository<ContactAudit, Long> {
}
