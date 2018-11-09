package com.apress.prospring4.ch11.jsr_349;

import com.apress.prospring4.ch11.validator.Contact;
import com.apress.prospring4.ch11.validator.ContactValidator;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jsr349Example {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch11/jsr_349/app-context-xml.xml");
        ctx.refresh();

        MyBeanValidationService myBeanValidationService = ctx.getBean("myBeanValidationService", MyBeanValidationService.class);

        Customer customer = new Customer();
        customer.setFirstName("C");
        customer.setLastName("Schaefer");
        customer.setCustomType(null);
        customer.setGender(null);

        validateCustomer(customer, myBeanValidationService);

    }

    private static void validateCustomer(Customer customer, MyBeanValidationService myBeanValidationService) {
        Set<ConstraintViolation<Customer>> violations = new HashSet<ConstraintViolation<Customer>>();
        violations = myBeanValidationService.validateCustomer(customer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Customer>> violations) {
        System.out.println("No. of violations: " + violations.size());
        for (ConstraintViolation<Customer> violation : violations) {
            System.out.println("Validation error for property: " + violation.getPropertyPath() +
                            " with value: " + violation.getInvalidValue() +
                            " with error message: " + violation.getMessage());
        }

    }
}
