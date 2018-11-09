package com.apress.prospring4.ch11.jsr_349;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service("myBeanValidationService")
public class MyBeanValidationService {
    @Autowired
    private Validator validator;

    public Set<ConstraintViolation<Customer>> validateCustomer(Customer customer){
        return validator.validate(customer);
    }
}
