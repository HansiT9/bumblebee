package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.entity.AdminEntity;
import com.system.bumblebee.repositories.AdminRepository;
import com.system.bumblebee.services.AuthService;
import com.system.bumblebee.util.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Declare the AuthServiceImpl class as a Spring service
@Service
public class AuthServiceImpl implements AuthService {
    // Inject the AdminRepository dependency
    @Autowired
    private AdminRepository adminRepository;

    // Implement the authenticateAdmin method of the AuthService interface
    @Override
    public boolean authenticateAdmin(Admin admin) {
        // Retrieve the AdminEntity from the repository using the email provided in the Admin DTO
        AdminEntity adminEntity = adminRepository.findByEmail(admin.getEmail());

        // Hash the password provided in the Admin DTO using the PasswordEncrypt utility class
        String hashedPassword = PasswordEncrypt.hashPassword(admin.getPassword());

        // Compare the hashed password with the password stored in the retrieved AdminEntity
        return hashedPassword.equals(adminEntity.getPassword());
    }
}
