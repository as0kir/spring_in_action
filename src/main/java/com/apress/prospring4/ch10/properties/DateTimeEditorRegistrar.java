package com.apress.prospring4.ch10.properties;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.time.format.DateTimeFormatter;

public class DateTimeEditorRegistrar implements PropertyEditorRegistrar {
    private DateTimeFormatter dateTimeFormatter;

    public DateTimeEditorRegistrar(String dateFormatPattern) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public void registerCustomEditors(PropertyEditorRegistry registry) {

    }
}
