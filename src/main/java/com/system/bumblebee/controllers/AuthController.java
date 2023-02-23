package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.services.AuthService;
import com.system.bumblebee.services.RegistrationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RegistrationService registerService;

    public AuthController(AuthService authService, RegistrationService registerService) {
        this.authService = authService;
        this.registerService = registerService;
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

    @GetMapping("/customer/validate")
    public ResponseEntity<?> validateEmail(@RequestParam String email) {
        boolean isAvailable = registerService.checkEmail(email);

        if (!isAvailable) {
            return ResponseEntity.status(HttpStatus.OK).body("Email Available!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exist!");
        }
    }

    @PostMapping("/customer/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        boolean registered = registerService.registerCustomer(customer);

        if (registered) {
            return ResponseEntity.status(HttpStatus.OK).body("Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }
}
