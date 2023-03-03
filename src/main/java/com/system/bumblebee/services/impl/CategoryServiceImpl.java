package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Category;
import com.system.bumblebee.entity.CategoryEntity;
import com.system.bumblebee.repositories.CategoryRepository;
import com.system.bumblebee.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Define the class and implement the interface
@Service
public class CategoryServiceImpl implements CategoryService {
    // Autowire the repository
    @Autowired
    private CategoryRepository categoryRepository;

    // Check if the category name exists
    @Override
    public boolean checkBrandNameExists(String categoryName) {
        Optional<CategoryEntity> brand = categoryRepository.findByCategoryName(categoryName);

        return brand.isPresent();
    }

    // Save a category
    @Override
    public boolean saveCategory(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        // Set the category name and logo
        categoryEntity.setCategoryName(category.getCategoryName());
        categoryEntity.setCategoryLogo(category.getCategoryLogo());

        // Save the category and return true if successful
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return savedCategory != null;
    }

    // Fetch all categories
    @Override
    public List<CategoryEntity> fetchAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();

        return categories;
    }

    // Remove a category by id
    @Override
    public boolean removeCategory(int id) {
        // Delete the category by id and check if it still exists
        categoryRepository.deleteById((long) id);
        boolean exist = categoryRepository.existsById((long) id);

        return exist;
    }

    // Update a category name by id
    @Override
    public boolean updateCategoryNameById(Long id, Category category) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            CategoryEntity categoryEntity = categoryOptional.get();

            // Set the new category name and logo
            categoryEntity.setCategoryName(category.getCategoryName());
            categoryEntity.setCategoryLogo(category.getCategoryLogo());

            // Save the updated category and return true if successful
            CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
            return savedCategory != null;
        }
        return false;
    }

    @Override
    public long getCategoryCount() {
        return categoryRepository.count();
    }
}
