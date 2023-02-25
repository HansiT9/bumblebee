package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByCategoryName(String categoryName);

    CategoryEntity save(CategoryEntity category);
}
