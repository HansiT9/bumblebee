// Define the package and import necessary classes
package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Admin;
import com.system.bumblebee.dto.Customer;
import com.system.bumblebee.factory.ServiceFactory;
import com.system.bumblebee.services.AuthService;
import com.system.bumblebee.services.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Define the RestController and set the base URL
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    // Inject the necessary services
//    private final AuthService authService; //
//    private final RegistrationService registerService;

    @Autowired
    private ServiceFactory factory;

//     Constructor to inject the services
//    public AuthController(RegistrationService registerService) {
//        this.authService = factory.authService();
//        this.registerService = factory.registrationService();
//    }

    // Endpoint to handle login requests for admin users
    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin admin) {
        System.out.println(admin.toString());

        AuthService authService = factory.authService();

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
    @GetMapping("/admin/email/validate")
    public ResponseEntity<?> validateEmailAdmin(@RequestParam String email) {

        RegistrationService registrationService = factory.registrationService();

        // Check if the email is available for registration
        boolean isAvailable = registrationService.checkEmailAdmin(email);

        logger.info("Is available " + isAvailable);

        // Check if the email is available and return a response
        if (!isAvailable) {
            return ResponseEntity.status(HttpStatus.OK).body("Email Available!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exist!");
        }
    }

    // Endpoint to handle nic validation requests for customer users
    @GetMapping("/customer/nic/validate/{nic}")
    public ResponseEntity<?> validateNicCustomer(@PathVariable String nic) {
        RegistrationService registrationService = factory.registrationService();

        boolean isAvailable = registrationService.checkNicCustomer(nic);

        if (!isAvailable) {
            return ResponseEntity.status(HttpStatus.OK).body("Nic Available!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nic Already Exist!");
        }
    }

    // Endpoint to handle email validation requests for customer users
    @GetMapping("/customer/email/validate")
    public ResponseEntity<?> validateEmailCustomer(@RequestParam String email) {
        RegistrationService registrationService = factory.registrationService();

        // Check if the email is available for registration
        boolean isAvailable = registrationService.checkEmailCustomer(email);

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

        RegistrationService registrationService = factory.registrationService();

        System.out.println(customer.toString());

        // Register the Customer
        boolean registered = registrationService.registerCustomer(customer);

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
        RegistrationService registrationService = factory.registrationService();

        System.out.println(admin.toString());

        // Register the admin user
        boolean registered = registrationService.registerAdmin(admin);

        // Check if the registered and return a response
        if (registered) {
            return ResponseEntity.status(HttpStatus.OK).body("Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }
}
