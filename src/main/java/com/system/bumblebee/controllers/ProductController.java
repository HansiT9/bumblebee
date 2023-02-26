package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Product;
import com.system.bumblebee.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createBrand(@RequestBody Product product) {
        boolean created = productService.saveProduct(product);

        if (created) {
            return ResponseEntity.status(HttpStatus.OK).body("Product Created");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }

    @GetMapping("/count/product_for_brand")
    public ResponseEntity<?> fetchProductCountForBrand() {
        Map<String, Long> productCount = productService.getProductCountsByBrand();

        if (!productCount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(productCount);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    @GetMapping("/count/categorie_for_brand")
    public ResponseEntity<?> fetchCategoryCountForBrand() {
        Map<String, Long> categoryCount = productService.getCategoryCountsByBrand();

        if (!categoryCount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryCount);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }
}
