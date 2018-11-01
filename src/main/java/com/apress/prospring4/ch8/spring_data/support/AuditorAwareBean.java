package com.apress.prospring4.ch8.spring_data.support;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareBean implements AuditorAware<String> {
    public String getCurrentAuditor() {
        return "prospring4";
    }
}
