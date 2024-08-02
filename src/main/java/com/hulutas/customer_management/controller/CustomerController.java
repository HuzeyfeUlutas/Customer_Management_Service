package com.hulutas.customer_management.controller;

import com.hulutas.customer_management.dto.CustomerDTO;
import com.hulutas.customer_management.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get all customers", description = "Fetches all customers from the database")
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Operation(summary = "Add customer", description = "Create customer in database")
    @PostMapping
    public CustomerDTO addCustomer(@Valid @RequestBody CustomerDTO customer) {
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("/{citizenNumber}")
    public ResponseEntity<Void> deleteCustomerByCitizenNumber(@PathVariable Long citizenNumber) {
        customerService.deleteCustomerByCitizenNumber(citizenNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{citizenNumber}")
    public ResponseEntity<CustomerDTO> getCustomerByCitizenNumber(@PathVariable Long citizenNumber) {
        CustomerDTO customerDTO = customerService.getCustomerByCitizenNumber(citizenNumber);
        return ResponseEntity.ok(customerDTO);
    }

    @PatchMapping("/status")
    public ResponseEntity<Void> updateCustomerStatus(@RequestParam Long citizenNumber, @RequestParam boolean isActive) {
        customerService.updateCustomerStatus(citizenNumber, isActive);
        return ResponseEntity.ok().build();
    }
}
