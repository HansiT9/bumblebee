package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authenticateAdmin(Admin admin) {
        return true;
    }

    @Override
    public boolean registerCustomer(Customer customer) {
        return true;
    }
}
