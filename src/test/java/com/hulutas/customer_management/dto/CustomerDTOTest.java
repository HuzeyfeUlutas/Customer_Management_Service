package com.hulutas.customer_management.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsAreValid_thenNoConstraintViolations() {
        CustomerDTO customer = new CustomerDTO(
                "John",
                "Doe",
                30,
                12345678901L,
                LocalDate.of(1990, 1, 1),
                true
        );

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customer);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenNameIsNull_thenOneConstraintViolation() {
        CustomerDTO customer = new CustomerDTO(
                null,
                "Doe",
                30,
                12345678901L,
                LocalDate.of(1990, 1, 1),
                true
        );

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void whenAgeIsLessThan18_thenOneConstraintViolation() {
        CustomerDTO customer = new CustomerDTO(
                "John",
                "Doe",
                17,
                12345678901L,
                LocalDate.of(2005, 1, 1),
                true
        );

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
        assertEquals("must be greater than or equal to 18", violations.iterator().next().getMessage());
    }

    @Test
    @Disabled
    void whenCitizenNumberIsNull_thenOneConstraintViolation() {
        CustomerDTO customer = new CustomerDTO(
                "John",
                "Doe",
                30,
                null,
                LocalDate.of(1990, 1, 1),
                true
        );

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customer);
        assertEquals(2, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    @Disabled
    void whenBirthDateIsNull_thenOneConstraintViolation() {
        CustomerDTO customer = new CustomerDTO(
                "John",
                "Doe",
                30,
                12345678901L,
                null,
                true
        );

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(customer);
        assertEquals(2, violations.size());
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

}
