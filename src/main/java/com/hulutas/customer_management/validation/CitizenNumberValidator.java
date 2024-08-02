package com.hulutas.customer_management.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CitizenNumberValidatorImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CitizenNumberValidator {
    String message() default "TC numarası 11 haneli olmalıdır.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
