// Define the package and import necessary classes
package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.services.AuthService;
import com.system.bumblebee.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Define the RestController and set the base URL
@RestController
@RequestMapping("/auth")
public class AuthController {

    // Inject the necessary services
    private final AuthService authService; //
    private final RegistrationService registerService;

    // Constructor to inject the services
    public AuthController(AuthService authService, RegistrationService registerService) {
        this.authService = authService;
        this.registerService = registerService;
    }

    // Endpoint to handle login requests for admin users
    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin admin) {
        // Authenticate the admin user
        boolean authenticated = authService.authenticateAdmin(admin);

        // Check if the user is authenticated and return a response
        if (authenticated) {
            return ResponseEntity.status(HttpStatus.OK).body(admin.getEmail());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    // Endpoint to handle email validation requests for admin users
    @GetMapping("/admin/validate")
    public ResponseEntity<?> validateEmailAdmin(@RequestParam String email) {
        // Check if the email is available for registration
        boolean isAvailable = registerService.checkEmailAdmin(email);

        // Check if the email is available and return a response
        if (!isAvailable) {
            return ResponseEntity.status(HttpStatus.OK).body("Email Available!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exist!");
        }
    }

    // Endpoint to handle email validation requests for customer users
    @GetMapping("/customer/validate")
    public ResponseEntity<?> validateEmailCustomer(@RequestParam String email) {
        // Check if the email is available for registration
        boolean isAvailable = registerService.checkEmailCustomer(email);

        // Check if the email is available and return a response
        if (!isAvailable) {
            return ResponseEntity.status(HttpStatus.OK).body("Email Available!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exist!");
        }
    }

    // Endpoint to handle register requests for customer
    @PostMapping("/customer/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        // Register the Customer
        boolean registered = registerService.registerCustomer(customer);

        // Check if the registered and return a response
        if (registered) {
            return ResponseEntity.status(HttpStatus.OK).body("Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }

    // Endpoint to handle register requests for admin users
    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin (@RequestBody Admin admin) {
        // Register the admin user
        boolean registered = registerService.registerAdmin(admin);

        // Check if the registered and return a response
        if (registered) {
            return ResponseEntity.status(HttpStatus.OK).body("Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }
}
