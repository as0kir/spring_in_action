package com.apress.prospring4.ch8.service;

import com.apress.prospring4.ch8.dto.ContactSummary;

import java.util.List;

public interface ContactSummaryService {
    List<ContactSummary> findAll();
}
