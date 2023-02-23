package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin admin, HttpSession session) {
        boolean authenticated = authService.authenticateAdmin(admin);

        if (authenticated) {
            session.setAttribute("email", admin.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(admin.getEmail());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/customer/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        boolean registered = authService.registerCustomer(customer);

        if (registered) {
            return ResponseEntity.status(HttpStatus.OK).body("Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }
}
