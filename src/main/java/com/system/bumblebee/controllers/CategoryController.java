// Define the package and import necessary classes
package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Category;
import com.system.bumblebee.entity.CategoryEntity;
import com.system.bumblebee.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define the RestController and set the base URL
@RestController
@RequestMapping("/category")
public class CategoryController {
    // Inject the necessary services
    private final CategoryService categoryService;

    // Constructor to inject the services
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Define a GET endpoint to validate the category name
    @GetMapping("/name/validate")
    public ResponseEntity<?> validateCategoryName(@RequestParam String categoryName) {
        // Check if the category name already exists
        boolean exists = categoryService.checkBrandNameExists(categoryName);

        if (exists) {
            // Return a conflict response if the category name exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category Name Already Exists");
        } else {
            // Return an OK response if the category name is available
            return ResponseEntity.status(HttpStatus.OK).body("Category Name available");
        }
    }

    // Define a GET endpoint to fetch all categories
    @GetMapping("/all")
    public ResponseEntity<?> fetchAllCategories() {
        // Fetch all categories from the category service
        List<CategoryEntity> category = categoryService.fetchAllCategories();

        if (!category.isEmpty()) {
            // Return an OK response with the category list if categories exist
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } else {
            // Return a NO_CONTENT response if there are no categories
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
        }
    }

    // Define a POST endpoint to create a new category
    @PostMapping("/new")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        // Save the new category using the category service
        boolean created = categoryService.saveCategory(category);

        if (created) {
            // Return an OK response if the category is successfully created
            return ResponseEntity.status(HttpStatus.OK).body("Brand Created");
        } else {
            // Return an INTERNAL_SERVER_ERROR response if there is an error creating the category
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }

    // Define a DELETE endpoint to remove a single category by ID
    @DeleteMapping("/remove/single/{id}")
    public ResponseEntity<?> removeAllWithCategoryId(@PathVariable String id) {
        // Remove the category with the specified ID using the category service
        boolean deleted = categoryService.removeCategory(Integer.parseInt(id));

        if (deleted) {
            // Return an OK response if the category is successfully deleted
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted under brand id " + id);
        } else {
            // Return an OK response if there is an error deleting the category
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong on our side");
        }
    }

    // Define a PUT endpoint to update a single category by ID
    @PutMapping("/update/single/{id}")
    public ResponseEntity<?> updateBrandNameById(@PathVariable Long id, @RequestBody Category category) {
        // Update the category name for the specified ID using the category service
        boolean updatedCategory = categoryService.updateCategoryNameById(id, category);

        if (updatedCategory) {
            // Return an OK response if the category name is successfully updated
            return ResponseEntity.status(HttpStatus.OK).body("Category name updated under id " + id);
        } else {
            // Return an OK response if there is
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong on our side");
        }
    }
}
