package com.system.bumblebee.services.impl;

import com.system.bumblebee.entity.CustomerEntity;
import com.system.bumblebee.repositories.CustomerRepository;
import com.system.bumblebee.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Fetch all customers from database
    @Override
    public List<CustomerEntity> fetchAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();

        return customers;
    }

    // Fetch a single customer by ID from database
    @Override
    public Optional<CustomerEntity> fetchSingleCustomer(Long cusID) {
        Optional<CustomerEntity> customer = customerRepository.findById(cusID);

        return customer;
    }

    @Override
    public long getCustomerCount() {
        return customerRepository.count();
    }
}
