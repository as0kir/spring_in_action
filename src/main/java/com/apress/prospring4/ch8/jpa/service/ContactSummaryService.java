package com.apress.prospring4.ch8.jpa.service;

import com.apress.prospring4.ch8.jpa.dto.ContactSummary;

import java.util.List;

public interface ContactSummaryService {
    List<ContactSummary> findAll();
}
