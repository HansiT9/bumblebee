package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.entity.AdminEntity;
import com.system.bumblebee.repositories.AdminRepository;
import com.system.bumblebee.services.AuthService;
import com.system.bumblebee.util.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean authenticateAdmin(Admin admin) {
        AdminEntity adminEntity = adminRepository.findByEmail(admin.getEmail());

        String hashedPassword = PasswordEncrypt.hashPassword(admin.getPassword());

        return hashedPassword.equals(adminEntity.getPassword());
    }
}
