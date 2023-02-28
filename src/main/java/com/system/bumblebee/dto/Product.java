package com.system.bumblebee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Add Lombok annotation to generate getters, setters, and toString method
@AllArgsConstructor // Add Lombok annotation to generate a constructor with all arguments
@NoArgsConstructor // Add Lombok annotation to generate a constructor with no arguments
public class Product {
    private String productName; // Define a private String field for product name
    private String productUrl; // Define a private String field for product logo url
    private String productBrandName; // Define a private String field for product brand name
    private String productCategoryName; // Define a private String field for product category name
    private int productQty; // Define a private String field for product qty
}
