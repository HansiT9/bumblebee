// Define the package and import necessary classes
package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Brand;
import com.system.bumblebee.entity.BrandEntity;
import com.system.bumblebee.services.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define the RestController and set the base URL
@RestController
@RequestMapping("/brand")
public class BrandController {

    // Inject the necessary services
    private final BrandService brandService;

    // Constructor to inject the services
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/count")
    public long getBrandCount() {
        return brandService.getBrandCount();
    }

    // Define the GET mapping to validate the brand name
    @GetMapping("/name/validate")
    public ResponseEntity<?> validateBrandName(@RequestParam String brandName) {
        boolean exists = brandService.checkBrandNameExists(brandName);

        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Brand Name Already Exists");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Brand Name available");
        }
    }

    // Define the GET mapping to fetch all brands
    @GetMapping("/all")
    public ResponseEntity<?> fetchAllBrands() {
        List<BrandEntity> brands = brandService.fetchAllBrands();

        if (!brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(brands);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    // Define the POST mapping to create a new brand
    @PostMapping("/new")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand) {
        boolean created = brandService.saveBrand(brand);

        if (created) {
            return ResponseEntity.status(HttpStatus.OK).body("Brand Created");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }

    // Define the DELETE mapping to remove a brand by id
    @DeleteMapping("/remove/single/{id}")
    public ResponseEntity<?> removeAllWithId(@PathVariable String id) {
        boolean deleted = brandService.removeBrand(Integer.parseInt(id));

        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Brand deleted under brand id " + id);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong on our side");
        }
    }

    // Define the PUT mapping to update a brand name by id
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
