// Define the package and import necessary classes
package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Product;
import com.system.bumblebee.entity.ProductEntity;
import com.system.bumblebee.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Define the RestController and set the base URL
@RestController
@RequestMapping("/product")
public class ProductController {
    // Inject the necessary services
    private final ProductService productService;

    // Constructor to inject the services
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Define a POST endpoint to create a new product
    @PostMapping("/new")
    public ResponseEntity<?> createBrand(@RequestBody Product product) {
        boolean created = productService.saveProduct(product);

        if (created) {
            return ResponseEntity.status(HttpStatus.OK).body("Product Created");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }

    // Define a GET endpoint to fetch all product
    @GetMapping("/all")
    public ResponseEntity<?> fetchAllCategories() {
        List<ProductEntity> product = productService.fetchAllProducts();

        if (!product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    // Define a GET endpoint to fetch product count for brand
    @GetMapping("/count/product_for_brand")
    public ResponseEntity<?> fetchProductCountForBrand() {
        Map<String, Long> productCount = productService.getProductCountsByBrand();

        if (!productCount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(productCount);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    // Define a GET endpoint to fetch product count for category
    @GetMapping("/count/product_for_category")
    public ResponseEntity<?> fetchProductCountForCategory() {
        Map<String, Long> categoryCount = productService.getProductCountsByCategory();

        if (!categoryCount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryCount);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    // Define a GET endpoint to fetch category count for brand
    @GetMapping("/count/categorie_for_brand")
    public ResponseEntity<?> fetchCategoryCountForBrand() {
        Map<String, Long> categoryCount = productService.getCategoryCountsByBrand();

        if (!categoryCount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryCount);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    // Define a GET endpoint to fetch brand count for category
    @GetMapping("/count/brand_for_category")
    public ResponseEntity<?> fetchBrandCountForCategory() {
        Map<String, Long> brandCount = productService.getBrandCountsByCategory();

        if (!brandCount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(brandCount);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    // Define a DELETE endpoint to remove all products has the brand name
    @DeleteMapping("/remove/all_equals_brandname/{brandName}")
    public ResponseEntity<?> removeAllWithBrandName(@PathVariable String brandName) {
        boolean deleted = productService.deleteAllWithBrandName(brandName);

        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Products deleted under brand name " + brandName);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong on our side");
        }
    }

    // Define a DELETE endpoint to remove all products has the category name
    @DeleteMapping("/remove/all_equals_categoryname/{categoryName}")
    public ResponseEntity<?> removeAllWithCategoryName(@PathVariable String categoryName) {
        boolean deleted = productService.deleteAllWithCategoryName(categoryName);

        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Products deleted under category name " + categoryName);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong on our side");
        }
    }

    // Define a DELETE endpoint to remove single product with id
    @DeleteMapping("/remove/single/{id}")
    public ResponseEntity<?> removeAllWithProductId(@PathVariable String id) {
        boolean deleted = productService.removeProduct(Integer.parseInt(id));

        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted under brand id " + id);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong on our side");
        }
    }

    // Define a PUT endpoint to update single product with id
    @PutMapping("/update/single/{id}")
    public ResponseEntity<?> updateBrandNameById(@PathVariable Long id, @RequestBody Product product) {
        boolean updatedProduct = productService.updateProductNameById(id, product);

        if (updatedProduct) {
            return ResponseEntity.status(HttpStatus.OK).body("Product updated under id " + id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong on our side");
        }
    }

    // Define a PUT endpoint to update multiple products brand name to a new brand name
    @PutMapping("/update/multiple/brand/{currentBrandName}/{newBrandName}")
    public ResponseEntity<?> updateProductBrandNames(@PathVariable String currentBrandName, @PathVariable String newBrandName) {
        boolean updated = productService.updateProductBrandNames(currentBrandName, newBrandName);

        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Products under brand name " + currentBrandName + " updated to " + newBrandName);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong on our side");
        }
    }

    // Define a PUT endpoint to update multiple products category name to a new category name
    @PutMapping("/update/multiple/category/{currentCategoryName}/{newCategoryName}")
    public ResponseEntity<?> updateProductCategoryNames(@PathVariable String currentCategoryName, @PathVariable String newCategoryName) {
        boolean updated = productService.updateProductCategoryNames(currentCategoryName, newCategoryName);

        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Products under brand name " + currentCategoryName + " updated to " + newCategoryName);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong on our side");
        }
    }

    @GetMapping("/count")
    public long getBrandCount() {
        return productService.getProductCount();
    }
}
