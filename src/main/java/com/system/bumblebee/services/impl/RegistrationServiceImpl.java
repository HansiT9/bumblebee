package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.entity.AdminEntity;
import com.system.bumblebee.entity.CustomerEntity;
import com.system.bumblebee.repositories.AdminRepository;
import com.system.bumblebee.repositories.CustomerRepository;
import com.system.bumblebee.services.RegistrationService;
import com.system.bumblebee.util.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    // Injecting CustomerRepository bean
    @Autowired
    private CustomerRepository customerRepository;

    // Injecting AdminRepository bean
    @Autowired
    private AdminRepository adminRepository;

    // Check if a customer with the given email exists in the database.
    @Override
    public boolean checkEmailCustomer(String email) {
        // Look for the customer by email
        CustomerEntity customerEntity = customerRepository.findByEmail(email);

        // If the customer was not found, return false
        if (customerEntity == null) {
            return false;
        }

        // If the customer was found, check if the email matches the given email and return the result
        return customerEntity.getEmail().equals(email);
    }

    // Check if an admin with the given email exists in the database.
    @Override
    public boolean checkEmailAdmin(String email) {
        AdminEntity adminEntity = adminRepository.findByEmail(email);

        if (adminEntity == null) {
            return false;
        }
        return adminEntity.getEmail().equals(email);
    }

    // Register a new admin with the given information.
    @Override
    public boolean registerAdmin(Admin admin) {
        AdminEntity adminEntity = new AdminEntity();

        // Hash the admin password
        String hashedPassword = PasswordEncrypt.hashPassword(admin.getPassword());

        adminEntity.setEmail(admin.getEmail());
        adminEntity.setPassword(hashedPassword);

        // Save the new admin entity in the database and return the result
        AdminEntity savedAdmin = adminRepository.save(adminEntity);

        return savedAdmin != null;
    }

    // Register a new customer with the given information.
    @Override
    public boolean registerCustomer(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();

        // Hash the customer password
        String hashedPassword = PasswordEncrypt.hashPassword(customer.getPassword());

        // Set the customer information in the customer entity
        customerEntity.setFullName(customer.getCustomerFullName());
        customerEntity.setDob(customer.getDob());
        customerEntity.setEmail(customer.getCustomerEmail());
        customerEntity.setPassword(hashedPassword);
        customerEntity.setInstallmentPlan(customer.getInstallmentPlan());

        // If the customer has an installment plan, set the initial loan balance and used amount
        if (!customer.getInstallmentPlan().equals("n/a")) {
            customerEntity.setLoanBalance(15000.0);
            customerEntity.setUsedAmount(0.0);
        } else {
            customerEntity.setLoanBalance(0.0);
            customerEntity.setUsedAmount(0.0);
        }

        // Save the new customer entity in the database and return the result
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        return savedCustomer != null;
    }
}
