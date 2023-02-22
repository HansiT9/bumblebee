package com.system.bumblebee.services;


import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.entity.AdminEntity;

public interface AuthService {
    AdminEntity authenticateAdmin(Admin admin);

    boolean registerCustomer(Customer customer);
}
