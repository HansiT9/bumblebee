package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Brand;
import com.system.bumblebee.services.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/name/validate")
    public ResponseEntity<?> validateBrandName(@RequestParam String brandName) {
        System.out.println(brandName);
        boolean exists = brandService.checkBrandNameExists(brandName);

        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Brand Name Already Exists");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Brand Name available");
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand) {
        boolean created = brandService.saveBrand(brand);

        if (created) {
            return ResponseEntity.status(HttpStatus.OK).body("Brand Created");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }

}
