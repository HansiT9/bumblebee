package com.system.bumblebee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Add Lombok annotation to generate getters, setters, and toString method
@AllArgsConstructor // Add Lombok annotation to generate a constructor with all arguments
@NoArgsConstructor // Add Lombok annotation to generate a constructor with no arguments
public class Admin {
    private String email; // Define a private String field for email
    private String password; // Define a private String field for password
}
