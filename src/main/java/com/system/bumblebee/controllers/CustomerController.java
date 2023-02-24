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

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> fetchAllCustomers() {

        List<CustomerEntity> customerDetails = customerService.fetchAllCustomers();

        if (!customerDetails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerDetails);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    @GetMapping("/single")
    public ResponseEntity<?> fetchSingleCustomer(@RequestParam Long cusID) {
        Optional<CustomerEntity> customerEntity = customerService.fetchSingleCustomer(cusID);

        if (customerEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerEntity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Use Data not available");
        }
    }
}
