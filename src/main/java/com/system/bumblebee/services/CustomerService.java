package com.system.bumblebee.services;

import com.system.bumblebee.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerEntity> fetchAllCustomers();
    Optional<CustomerEntity> fetchSingleCustomer(Long cusID);
}
