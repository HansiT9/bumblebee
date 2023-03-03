package com.system.bumblebee.service;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.entity.AdminEntity;
import com.system.bumblebee.entity.CustomerEntity;
import com.system.bumblebee.repositories.AdminRepository;
import com.system.bumblebee.repositories.CustomerRepository;
import com.system.bumblebee.services.impl.RegistrationServiceImpl;
import com.system.bumblebee.util.PasswordEncrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkEmailCustomer_whenCustomerExists_shouldReturnTrue() {
        String email = "john@example.com";
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail(email);

        when(customerRepository.findByEmail(email)).thenReturn(customerEntity);

        boolean result = registrationService.checkEmailCustomer(email);

        Assertions.assertTrue(result);
    }

    @Test
    void checkEmailCustomer_whenCustomerDoesNotExist_shouldReturnFalse() {
        String email = "jane@example.com";

        when(customerRepository.findByEmail(email)).thenReturn(null);

        boolean result = registrationService.checkEmailCustomer(email);

        Assertions.assertFalse(result);
    }

    @Test
    void checkNicCustomer_whenCustomerExists_shouldReturnTrue() {
        String nic = "1234567890";
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setNic(nic);

        when(customerRepository.findByNic(nic)).thenReturn(customerEntity);

        boolean result = registrationService.checkNicCustomer(nic);

        Assertions.assertTrue(result);
    }

    @Test
    void checkNicCustomer_whenCustomerDoesNotExist_shouldReturnFalse() {
        String nic = "0987654321";

        when(customerRepository.findByNic(nic)).thenReturn(null);

        boolean result = registrationService.checkNicCustomer(nic);

        Assertions.assertFalse(result);
    }

    @Test
    void checkEmailAdmin_whenAdminExists_shouldReturnTrue() {
        String email = "admin@example.com";
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setEmail(email);

        when(adminRepository.findByEmail(email)).thenReturn(adminEntity);

        boolean result = registrationService.checkEmailAdmin(email);

        Assertions.assertTrue(result);
    }

    @Test
    void checkEmailAdmin_whenAdminDoesNotExist_shouldReturnFalse() {
        String email = "superadmin@example.com";

        when(adminRepository.findByEmail(email)).thenReturn(null);

        boolean result = registrationService.checkEmailAdmin(email);

        Assertions.assertFalse(result);
    }

    @Test
    void registerAdmin_whenAdminSaved_shouldReturnTrue() {
        String email = "newadmin@example.com";
        String password = "newpassword";
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);

        String hashedPassword = PasswordEncrypt.hashPassword(password);

        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setEmail(email);
        adminEntity.setPassword(hashedPassword);

        when(adminRepository.save(any(AdminEntity.class))).thenReturn(adminEntity);

        boolean result = registrationService.registerAdmin(admin);

        Assertions.assertTrue(result);
    }

}
