package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Category;
import com.system.bumblebee.entity.CategoryEntity;
import com.system.bumblebee.repositories.CategoryRepository;
import com.system.bumblebee.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean checkBrandNameExists(String categoryName) {
        Optional<CategoryEntity> brand = categoryRepository.findByCategoryName(categoryName);

        return brand.isPresent();
    }

    @Override
    public boolean saveCategory(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryName(category.getCategoryName());
        categoryEntity.setCategoryLogo(category.getCategoryLogo());

        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return savedCategory != null;
    }

    @Override
    public List<CategoryEntity> fetchAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();

        return categories;
    }

    @Override
    public boolean removeCategory(int id) {
        categoryRepository.deleteById((long) id);
        boolean exist = categoryRepository.existsById((long) id);

        return exist;
    }

    @Override
    public boolean updateCategoryNameById(Long id, Category category) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            CategoryEntity categoryEntity = categoryOptional.get();
            categoryEntity.setCategoryName(category.getCategoryName());
            categoryEntity.setCategoryLogo(category.getCategoryLogo());

            CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
            return savedCategory != null;
        }
        return false;
    }
}
