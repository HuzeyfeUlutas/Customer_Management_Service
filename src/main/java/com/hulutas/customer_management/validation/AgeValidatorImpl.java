package com.hulutas.customer_management.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidatorImpl implements ConstraintValidator<AgeValidator, LocalDate> {
    @Override
    public void initialize(AgeValidator age) {
    }

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
        if (birthdate == null) {
            return false;
        }
        return Period.between(birthdate, LocalDate.now()).getYears() >= 18;
    }
}
