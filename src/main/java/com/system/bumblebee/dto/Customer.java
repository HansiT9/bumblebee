package com.system.bumblebee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Add Lombok annotation to generate getters, setters, and toString method
@AllArgsConstructor // Add Lombok annotation to generate a constructor with all arguments
@NoArgsConstructor // Add Lombok annotation to generate a constructor with no arguments
public class Customer {
    private String customerFullName; // Define a private String field for customer full name
    private String Dob; // Define a private String field for date of birth
    private String nic; // Define a Private String field for nic
    private String customerEmail; // Define a private String field for email
    private String password; // Define a private String field for password
    private String installmentPlan; // Define a private String field for installment plan
}
