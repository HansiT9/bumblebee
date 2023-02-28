package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Defined a repository interface for CategoryEntity
@Repository // Spring Data annotation to mark the interface as a repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByCategoryName(String categoryName); // method to find category entity by category name
    CategoryEntity save(CategoryEntity category); // method to save a category entity
    List<CategoryEntity> findAll(); // method to find all category entities
    void deleteById(Long id); // method to delete category entity by id
    boolean existsById(Long id); // method to check if category entity exist by id
}
