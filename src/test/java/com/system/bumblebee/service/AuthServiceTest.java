package com.system.bumblebee.service;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.entity.AdminEntity;
import com.system.bumblebee.repositories.AdminRepository;
import com.system.bumblebee.services.impl.AuthServiceImpl;
import com.system.bumblebee.util.PasswordEncrypt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AuthServiceImpl authService;


    @Test
    public void testAuthenticateAdmin() {

        // Arrange
        String email = "asd@gmail.com";
        String password = "12345678a";
        String hashedPassword = PasswordEncrypt.hashPassword(password);

        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setEmail(email);
        adminEntity.setPassword(hashedPassword);

        when(adminRepository.findByEmail(email)).thenReturn(adminEntity);

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);

        // Act
        boolean result = authService.authenticateAdmin(admin);

        assertTrue(result);

    }

}
