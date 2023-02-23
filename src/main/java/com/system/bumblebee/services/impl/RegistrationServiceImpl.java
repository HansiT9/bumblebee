package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.entity.CustomerEntity;
import com.system.bumblebee.repositories.CustomerRepository;
import com.system.bumblebee.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean checkEmail(String email) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email);

        if (customerEntity == null) {
            return false;
        }
        return customerEntity.getEmail().equals(email);
    }

    @Override
    public boolean registerCustomer(Customer customer) {
        return false;
    }
}
