package com.system.bumblebee.services;

import com.system.bumblebee.dto.Category;

public interface CategoryService {
    boolean checkBrandNameExists(String categoryName);

    boolean saveCategory(Category category);
}
