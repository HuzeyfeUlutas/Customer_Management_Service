package com.hulutas.customer_management.repository;

import com.hulutas.customer_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    void deleteByCitizenNumber(Long citizenNumber);

    Customer findByCitizenNumber(Long citizenNumber);
}
