// Define the package and import necessary classes
package com.system.bumblebee.controllers;

import com.system.bumblebee.entity.CustomerEntity;
import com.system.bumblebee.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// Define the RestController and set the base URL
@RestController
@RequestMapping("/customer")
public class CustomerController {

    // Inject the necessary services
    private final CustomerService customerService;

    // Constructor to inject the services
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Endpoint to fetch all customer details
    @GetMapping("/all")
    public ResponseEntity<?> fetchAllCustomers() {
        // Call service to fetch all customer details
        List<CustomerEntity> customerDetails = customerService.fetchAllCustomers();

        // Return response with status and message
        if (!customerDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    // Endpoint to fetch a single customer detail
    @GetMapping("/single")
    public ResponseEntity<?> fetchSingleCustomer(@RequestParam Long cusID) {
        // Call service to fetch a single customer detail
        Optional<CustomerEntity> customerEntity = customerService.fetchSingleCustomer(cusID);

        // Return response with status and message
        if (customerEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerEntity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Use Data not available");
        }
    }
}
