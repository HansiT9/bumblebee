package com.system.bumblebee.services;


import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;

public interface AuthService {
    boolean authenticateAdmin(Admin admin);

    boolean registerCustomer(Customer customer);
}
