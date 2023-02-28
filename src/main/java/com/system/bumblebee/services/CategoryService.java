package com.system.bumblebee.services;

import com.system.bumblebee.dto.Category;
import com.system.bumblebee.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    boolean checkBrandNameExists(String categoryName);
    boolean saveCategory(Category category);
    boolean removeCategory(int id);
    List<CategoryEntity> fetchAllCategories();
    boolean updateCategoryNameById(Long id, Category category);
}
