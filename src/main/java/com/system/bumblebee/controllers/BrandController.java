package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Brand;
import com.system.bumblebee.entity.BrandEntity;
import com.system.bumblebee.services.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/name/validate")
    public ResponseEntity<?> validateBrandName(@RequestParam String brandName) {
        boolean exists = brandService.checkBrandNameExists(brandName);

        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Brand Name Already Exists");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Brand Name available");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> fetchAllBrands() {
        List<BrandEntity> brands = brandService.fetchAllBrands();

        if (!brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(brands);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
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

    @DeleteMapping("/remove/single/{id}")
    public ResponseEntity<?> removeAllWithBrandName(@PathVariable String id) {
        boolean deleted = brandService.removeBrand(Integer.parseInt(id));

        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Brand deleted under brand id " + id);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong on our side");
        }
    }

    @PutMapping("/update/single/{id}")
    public ResponseEntity<?> updateBrandNameById(@PathVariable Long id, @RequestBody Brand brand) {
        boolean updatedBrand = brandService.updateBrandNameById(id, brand);

        if (updatedBrand) {
            return ResponseEntity.status(HttpStatus.OK).body("Brand name updated under id " + id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong on our side");
        }
    }

}
