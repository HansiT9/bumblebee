package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.entity.CustomerEntity;
import com.system.bumblebee.repositories.CustomerRepository;
import com.system.bumblebee.services.RegistrationService;
import com.system.bumblebee.util.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean checkEmail(String email) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email);

        if (customerEntity == null) {
            return false;
        }
        return customerEntity.getEmail().equals(email);
    }

    @Override
    public boolean registerCustomer(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();

        String hashedPassword = PasswordEncrypt.hashPassword(customer.getPassword());

        customerEntity.setFullName(customer.getCustomerFullName());
        customerEntity.setDob(customer.getDob());
        customerEntity.setEmail(customer.getCustomerEmail());
        customerEntity.setPassword(hashedPassword);
        customerEntity.setInstallmentPlan(customer.getInstallmentPlan());

        if (!customer.getInstallmentPlan().equals("n/a")) {
            customerEntity.setLoanBalance(15000.0);
            customerEntity.setUsedAmount(0.0);
        } else {
            customerEntity.setLoanBalance(0.0);
            customerEntity.setUsedAmount(0.0);
        }

        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        return savedCustomer != null;
    }
}
