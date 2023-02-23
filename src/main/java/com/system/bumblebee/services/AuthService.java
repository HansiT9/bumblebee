package com.system.bumblebee.services;


import com.system.bumblebee.dto.Admin;

public interface AuthService {
    boolean authenticateAdmin(Admin admin);
}
