package com.hulutas.customer_management.dto;

import com.hulutas.customer_management.validation.AgeValidator;
import com.hulutas.customer_management.validation.CitizenNumberValidator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerDTO (
        @NotNull
        String name,
        @NotNull
        String surname,
        @NotNull
        @Min(18)
        int age,
        @NotNull
        @CitizenNumberValidator
        Long citizenNumber,
        @NotNull
        @AgeValidator
        LocalDate birth_date,
        boolean isActive) {
}
