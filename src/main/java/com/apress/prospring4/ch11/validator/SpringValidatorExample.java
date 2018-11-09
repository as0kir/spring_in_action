package com.apress.prospring4.ch11.validator;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpringValidatorExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch11/validator/app-context-xml.xml");
        ctx.refresh();

        Contact contact = new Contact();
        contact.setFirstName(null);
        contact.setLastName("Schaefer");

        Validator contactValidator = ctx.getBean("contactValidator", ContactValidator.class);
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(contact, "Chris");
        ValidationUtils.invokeValidator(contactValidator, contact, result);

        List<ObjectError> errors = result.getAllErrors();
        System.out.println("No of validation errors: " + errors.size());
        for (ObjectError error : errors) {
            System.out.println(error.getCode());
        }
    }
}
