package com.system.bumblebee.services;

import com.system.bumblebee.dto.Customer;

public interface RegistrationService {

    boolean checkEmail(String email);
    boolean registerCustomer(Customer customer);
}
