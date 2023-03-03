package com.system.bumblebee.service;

import com.system.bumblebee.dto.Category;
import com.system.bumblebee.entity.CategoryEntity;
import com.system.bumblebee.repositories.CategoryRepository;
import com.system.bumblebee.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private CategoryEntity categoryEntity;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setCategoryName("test category");
        categoryEntity.setCategoryLogo("test logo");

        category = new Category();
        category.setCategoryName("test category");
        category.setCategoryLogo("test logo");
    }

    @Test
    @DisplayName("Test checkBrandNameExists returns true when category name exists")
    void testCheckCategoryNameExists_True() {
        when(categoryRepository.findByCategoryName(any(String.class))).thenReturn(Optional.of(categoryEntity));

        boolean result = categoryService.checkBrandNameExists(category.getCategoryName());

        assertTrue(result);
        verify(categoryRepository, times(1)).findByCategoryName(category.getCategoryName());
    }

    @Test
    @DisplayName("Test checkBrandNameExists returns false when category name does not exist")
    void testCheckCategoryNameExists_False() {
        when(categoryRepository.findByCategoryName(any(String.class))).thenReturn(Optional.empty());

        boolean result = categoryService.checkBrandNameExists(category.getCategoryName());

        assertFalse(result);
        verify(categoryRepository, times(1)).findByCategoryName(category.getCategoryName());
    }

    @Test
    @DisplayName("Test saveCategory returns true when category is saved successfully")
    void testSaveCategory_Success() {
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);

        boolean result = categoryService.saveCategory(category);

        assertTrue(result);
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    @DisplayName("Test saveCategory returns false when category is not saved successfully")
    void testSaveCategory_Failure() {
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(null);

        boolean result = categoryService.saveCategory(category);

        assertFalse(result);
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    @DisplayName("Test fetchAllCategories returns a list of categories")
    void testFetchAllCategories() {
        List<CategoryEntity> categoryList = new ArrayList<>();
        categoryList.add(categoryEntity);

        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<CategoryEntity> result = categoryService.fetchAllCategories();

        assertEquals(1, result.size());
        assertEquals(categoryEntity.getId(), result.get(0).getId());
        assertEquals(categoryEntity.getCategoryName(), result.get(0).getCategoryName());
        assertEquals(categoryEntity.getCategoryLogo(), result.get(0).getCategoryLogo());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test removeCategory returns true when category is removed")
    void testRemoveCategory() {
        int id = 1;

        doNothing().when(categoryRepository).deleteById((long) id);
        when(categoryRepository.existsById((long) id)).thenReturn(false);

        boolean result = categoryService.removeCategory(id);

        assertFalse(result);
        verify(categoryRepository, times(1)).deleteById((long) id);
        verify(categoryRepository, times(1)).existsById((long) id);
    }

    // Test updateCategoryNameById method
    @Test
    @DisplayName("Test updateCategoryNameById returns true when category name is updated")
    void testUpdateCategoryNameById() {
        long id = 1;
        Category category = new Category("New Category Name", "new-logo.png");

        Optional<CategoryEntity> categoryOptional = Optional.of(new CategoryEntity(id, "Old Category Name", "old-logo.png"));
        when(categoryRepository.findById(id)).thenReturn(categoryOptional);
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(new CategoryEntity(id, "New Category Name", "new-logo.png"));

        boolean result = categoryService.updateCategoryNameById(id, category);

        assertTrue(result);
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    // Test getCategoryCount method
    @Test
    @DisplayName("Test getCategoryCount returns the correct count")
    void testGetCategoryCount() {
        long expectedCount = 5;

        when(categoryRepository.count()).thenReturn(expectedCount);

        long result = categoryService.getCategoryCount();

        assertEquals(expectedCount, result);
        verify(categoryRepository, times(1)).count();
    }
}
