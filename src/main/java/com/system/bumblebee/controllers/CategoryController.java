package com.system.bumblebee.controllers;

import com.system.bumblebee.dto.Category;
import com.system.bumblebee.entity.CategoryEntity;
import com.system.bumblebee.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<?> fetchAllCategories() {
        List<CategoryEntity> category = categoryService.fetchAllCategories();

        if (!category.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data");
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

    @DeleteMapping("/remove/single/{id}")
    public ResponseEntity<?> removeAllWithCategoryId(@PathVariable String id) {
        boolean deleted = categoryService.removeCategory(Integer.parseInt(id));

        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted under brand id " + id);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong on our side");
        }
    }

    @PutMapping("/update/single/{id}")
    public ResponseEntity<?> updateBrandNameById(@PathVariable Long id, @RequestBody Category category) {
        System.out.println(id);
        System.out.println(category.getCategoryName());
        System.out.println(category.getCategoryLogo());

        boolean updatedCategory = categoryService.updateCategoryNameById(id, category);

        if (updatedCategory) {
            return ResponseEntity.status(HttpStatus.OK).body("Category name updated under id " + id);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong on our side");
        }
    }
}
