package com.system.bumblebee.service;

import com.system.bumblebee.entity.CustomerEntity;
import com.system.bumblebee.repositories.CustomerRepository;
import com.system.bumblebee.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    @DisplayName("Test fetchAllCustomers returns a list of customers")
    void testFetchAllCustomers() {
        List<CustomerEntity> customers = new ArrayList<>();
        customers.add(new CustomerEntity(1L, "John Doe", "1990-01-01", "123456789V", "johndoe@example.com",
                "password", 5000.0, 0.0, "Weekly"));
        customers.add(new CustomerEntity(2L, "Jane Doe", "1995-03-15", "987654321V", "janedoe@example.com",
                "password", 3000.0, 500.0, "Monthly"));

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerEntity> result = customerService.fetchAllCustomers();

        assertNotNull(result);
        assertEquals(customers.size(), result.size());
        assertEquals(customers.get(0).getFullName(), result.get(0).getFullName());
        assertEquals(customers.get(1).getEmail(), result.get(1).getEmail());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test fetchSingleCustomer returns a customer when given an ID")
    void testFetchSingleCustomer() {
        Long id = 1L;
        CustomerEntity customer = new CustomerEntity(id, "John Doe", "1990-01-01", "123456789V",
                "johndoe@example.com", "password", 1000.0, 500.0, "Monthly");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        Optional<CustomerEntity> result = customerService.fetchSingleCustomer(id);

        assertTrue(result.isPresent());
        assertEquals(customer.getId(), result.get().getId());
        assertEquals(customer.getFullName(), result.get().getFullName());
        assertEquals(customer.getDob(), result.get().getDob());
        assertEquals(customer.getNic(), result.get().getNic());
        assertEquals(customer.getEmail(), result.get().getEmail());
        assertEquals(customer.getPassword(), result.get().getPassword());
        assertEquals(customer.getLoanBalance(), result.get().getLoanBalance());
        assertEquals(customer.getUsedAmount(), result.get().getUsedAmount());
        assertEquals(customer.getInstallmentPlan(), result.get().getInstallmentPlan());
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test fetchSingleCustomer returns empty when given a non-existing ID")
    void testFetchSingleCustomer_NonExistingID() {
        Long id = 3L;

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CustomerEntity> result = customerService.fetchSingleCustomer(id);

        assertFalse(result.isPresent());
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test getCustomerCount returns the number of customers in the database")
    void testGetCustomerCount() {
        long count = 3L;

        when(customerRepository.count()).thenReturn(count);

        long result = customerService.getCustomerCount();

        assertEquals(count, result);
        verify(customerRepository, times(1)).count();
    }
}
