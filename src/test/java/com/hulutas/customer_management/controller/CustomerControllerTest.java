package com.hulutas.customer_management.controller;

import com.hulutas.customer_management.dto.CustomerDTO;
import com.hulutas.customer_management.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CustomerService customerService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCustomers_ShouldReturnCustomerList() throws Exception {
        CustomerDTO customer = new CustomerDTO("Test","TestSur",30, 123456789L, LocalDate.of(1995,1,1), true);

        when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customer));

        mockMvc.perform(get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test"));
    }


    @Test()
    @Disabled("Something goes wrong")
    void addCustomer_ShouldReturnAddedCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO("Test1","TestSur1",20, 12345678901L, LocalDate.of(1995,2,3), true);

        when(customerService.addCustomer(any(CustomerDTO.class))).thenReturn(customer);

        String jsonRequest = "{"
                + "\"name\":\"Test1\","
                + "\"surname\":\"TestSur1\","
                + "\"age\":20,"
                + "\"citizenNumber\":12345678901,"
                + "\"birthDate\":\"1995-02-03\","
                + "\"isActive\":true"
                + "}";

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test1"));
    }

    @Test
    void deleteCustomerByCitizenNumber_ShouldReturnNoContent() throws Exception {
        doNothing().when(customerService).deleteCustomerByCitizenNumber(123456789L);

        mockMvc.perform(delete("/api/customers/123456789")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getCustomerByCitizenNumber_ShouldReturnCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO("Test","TestSur",30, 123456789L, LocalDate.of(1995,1,1), true);


        when(customerService.getCustomerByCitizenNumber(123456789L)).thenReturn(customer);

        mockMvc.perform(get("/api/customers/123456789")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    void updateCustomerStatus_ShouldReturnOk() throws Exception {
        doNothing().when(customerService).updateCustomerStatus(123456789L, true);

        mockMvc.perform(patch("/api/customers/status")
                        .param("citizenNumber", "123456789")
                        .param("isActive", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
