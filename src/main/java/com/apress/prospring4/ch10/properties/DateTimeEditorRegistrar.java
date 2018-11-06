package com.apress.prospring4.ch10.properties;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditorSupport;

public class DateTimeEditorRegistrar implements PropertyEditorRegistrar {
    private DateTimeFormatter dateTimeFormatter;

    public DateTimeEditorRegistrar(String dateFormatPattern) {
        this.dateTimeFormatter = DateTimeFormat.forPattern(dateFormatPattern);
    }

    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(DateTime.class, new DateTimeEditor(dateTimeFormatter));
    }

    private static class DateTimeEditor extends PropertyEditorSupport {
        private DateTimeFormatter dateTimeFormatter;

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(DateTime.parse(text, dateTimeFormatter));
        }

        public DateTimeEditor(DateTimeFormatter dateTimeFormatter) {
            this.dateTimeFormatter = dateTimeFormatter;
        }
    }
}
