package com.hulutas.customer_management.service;

import com.hulutas.customer_management.dto.CustomerDTO;
import com.hulutas.customer_management.exception.ResourceNotFoundException;
import com.hulutas.customer_management.mapper.CustomerMapper;
import com.hulutas.customer_management.model.Customer;
import com.hulutas.customer_management.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final MernisService mernisService;

    public CustomerService(CustomerRepository customerRepository, MernisService mernisService, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.mernisService = mernisService;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerDTO addCustomer(CustomerDTO customer){
        logger.info("Adding new customer: {}", customer);

        boolean isCitizenValid = mernisService.validateCitizen(
                customer.citizenNumber(),
                customer.name(),
                customer.surname(),
                customer.birth_date().getYear()
        );

        if(!isCitizenValid){
            logger.warn("Invalid citizen information for TC: {}", customer.citizenNumber());
            throw new IllegalArgumentException("Invalid citizen information");
        }

        Customer newCustomer = customerMapper.customerDTOToCustomer(customer);

        Customer savedCustomer = customerRepository.save(newCustomer);

        CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        logger.info("Customer added successfully: {}", savedCustomer);

        return savedCustomerDTO;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        if(customers.isEmpty())
            throw new ResourceNotFoundException("Customers not found");

        return customers.stream().map(customer -> new CustomerDTO(customer.getName(),customer.getSurname(), customer.getAge(), customer.getCitizenNumber(), customer.getBirth_date(), customer.isActive())
        ).collect(Collectors.toList());
    }

    @Transactional
    public void deleteCustomerByCitizenNumber(Long citizenNumber) {
        logger.info("Deleting customer with citizen id: {}", citizenNumber);
        if (customerRepository.findByCitizenNumber(citizenNumber) == null) {
            logger.warn("Customer not found with id: {}", citizenNumber);
            throw new ResourceNotFoundException("Customer not found with citizen id: " + citizenNumber);
        }
        customerRepository.deleteByCitizenNumber(citizenNumber);
        logger.info("Customer deleted successfully with id: {}", citizenNumber);
    }

    @Transactional
    public CustomerDTO getCustomerByCitizenNumber(Long citizenNumber) {
        Customer customer = customerRepository.findByCitizenNumber(citizenNumber);
        if (customer == null) {
            logger.warn("Customer not found with id: {}", citizenNumber);
            throw new ResourceNotFoundException("Customer not found with citizen id: " + citizenNumber);
        }

        return customerMapper.customerToCustomerDTO(customer);
    }

    public void updateCustomerStatus(Long citizenNumber, boolean isActive) {
        logger.info("Updating customer citizen number: {}", citizenNumber);
        Customer customer = customerRepository.findByCitizenNumber(citizenNumber);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with citizen number: " + citizenNumber);
        }
        customer.setActive(isActive);
        Customer savedCustomer = customerRepository.save(customer);
        logger.info("Customer updated successfully: {}", savedCustomer);
    }

}
