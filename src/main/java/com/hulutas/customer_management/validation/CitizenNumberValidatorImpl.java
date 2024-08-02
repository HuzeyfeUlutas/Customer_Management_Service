package com.hulutas.customer_management.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CitizenNumberValidatorImpl implements ConstraintValidator<CitizenNumberValidator, Long> {
    @Override
    public void initialize(CitizenNumberValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long citizenNumber, ConstraintValidatorContext context) {
        String tcNo = String.valueOf(citizenNumber);
        if (tcNo == null) {
            return false;
        }
        return tcNo.length() == 11 && tcNo.matches("\\d+");
    }
}
