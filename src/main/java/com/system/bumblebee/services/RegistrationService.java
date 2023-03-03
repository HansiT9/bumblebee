package com.system.bumblebee.services;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;

public interface RegistrationService {

    boolean checkEmailCustomer(String email);
    boolean registerCustomer(Customer customer);
    boolean checkEmailAdmin(String email);
    boolean registerAdmin(Admin admin);
    boolean checkNicCustomer(String nic);
}
