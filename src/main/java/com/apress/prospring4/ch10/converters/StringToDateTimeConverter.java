package com.apress.prospring4.ch10.converters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

public class StringToDateTimeConverter implements Converter<String, DateTime>{
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateFormat;
    private String datePattern = DEFAULT_DATE_PATTERN;

    public String getDatePattern() {
        return datePattern;
    }

    @Autowired(required = false)
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init(){
        dateFormat = DateTimeFormat.forPattern(datePattern);
    }

    public DateTime convert(String s) {
        return dateFormat.parseDateTime(s);
    }
}
