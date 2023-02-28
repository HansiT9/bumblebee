package com.system.bumblebee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Add Lombok annotation to generate getters, setters, and toString method
@AllArgsConstructor // Add Lombok annotation to generate a constructor with all arguments
@NoArgsConstructor // Add Lombok annotation to generate a constructor with no arguments
public class Brand {
    private String brandName; // Define a private String field for brandName
    private String brandLogo; // Define a private String field for brandLogo
}
