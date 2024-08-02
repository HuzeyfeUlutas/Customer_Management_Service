package com.hulutas.customer_management.mapper;

import com.hulutas.customer_management.dto.CustomerDTO;
import com.hulutas.customer_management.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    @Mapping(target = "isActive", source = "active")
    CustomerDTO customerToCustomerDTO(Customer customer);
    @Mapping(target = "active", source = "isActive")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}
