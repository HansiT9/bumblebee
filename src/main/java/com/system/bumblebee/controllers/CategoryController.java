package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Category;
import com.system.bumblebee.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/name/validate")
    public ResponseEntity<?> validateCategoryName(@RequestParam String categoryName) {
        System.out.println("in category controller");
        boolean exists = categoryService.checkBrandNameExists(categoryName);

        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category Name Already Exists");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Category Name available");
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        boolean created = categoryService.saveCategory(category);

        if (created) {
            return ResponseEntity.status(HttpStatus.OK).body("Brand Created");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request, please try again later");
        }
    }
}
