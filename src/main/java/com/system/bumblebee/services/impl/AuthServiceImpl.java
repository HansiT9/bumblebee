package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.entity.AdminEntity;
import com.system.bumblebee.repositories.AdminRepository;
import com.system.bumblebee.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminEntity authenticateAdmin(Admin admin) {
        return adminRepository.findByEmail(admin.getEmail());
    }

    @Override
    public boolean registerCustomer(Customer customer) {
        return true;
    }
}
