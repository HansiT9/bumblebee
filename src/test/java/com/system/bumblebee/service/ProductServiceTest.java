package com.system.bumblebee.service;

import com.system.bumblebee.dto.Product;
import com.system.bumblebee.entity.ProductEntity;
import com.system.bumblebee.repositories.ProductRepository;
import com.system.bumblebee.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveProductTest() {
        // Create a mock product object
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductUrl("http://testurl.com");
        product.setProductBrandName("Test Brand");
        product.setProductCategoryName("Test Category");
        product.setProductQty(5);

        // Create a mock product entity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName("Test Product");
        productEntity.setProductUrl("http://testurl.com");
        productEntity.setProductBrandName("Test Brand");
        productEntity.setProductCategoryName("Test Category");
        productEntity.setProductQty(5);

        // Set up the mock repository to return the mock product entity when save() is called
        when(productRepository.save(productEntity)).thenReturn(productEntity);

        // Call the service method
        boolean result = productService.saveProduct(product);

        // Check that the service method returned true
        assertTrue(result);

        // Verify that the save() method of the mock repository was called once with the mock product entity
        verify(productRepository, times(1)).save(productEntity);
    }

    @Test
    public void getProductCountsByBrandTest() {
        // Create a mock result list
        List<Object[]> results = new ArrayList<>();
        Object[] result1 = {"Brand A", 10L};
        Object[] result2 = {"Brand B", 20L};
        results.add(result1);
        results.add(result2);

        // Set up the mock repository to return the mock result list when getProductCountsByBrand() is called
        when(productRepository.getProductCountsByBrand()).thenReturn(results);

        // Call the service method
        Map<String, Long> productCounts = productService.getProductCountsByBrand();

        // Check that the service method returned the correct map
        Map<String, Long> expectedCounts = new HashMap<>();
        expectedCounts.put("Brand A", 10L);
        expectedCounts.put("Brand B", 20L);
        assertEquals(expectedCounts, productCounts);

        // Verify that the getProductCountsByBrand() method of the mock repository was called once
        verify(productRepository, times(1)).getProductCountsByBrand();
    }

    @Test
    public void testGetProductCountsByCategory() {
        // Create mock data for the repository
        List<Object[]> results = new ArrayList<>();
        results.add(new Object[]{"Category 1", 5L});
        results.add(new Object[]{"Category 2", 8L});
        results.add(new Object[]{"Category 3", 3L});
        when(productRepository.getBrandCountsByCategory()).thenReturn(results);

        // Call the service method
        Map<String, Long> productCountsByCategory = productService.getProductCountsByCategory();

        // Verify the result
        assertEquals(3, productCountsByCategory.size());
        assertEquals(5L, productCountsByCategory.get("Category 1"));
        assertEquals(8L, productCountsByCategory.get("Category 2"));
        assertEquals(3L, productCountsByCategory.get("Category 3"));
    }

    @Test
    public void testGetCategoryCountsByBrand() {
        // Create mock data for the repository
        List<Object[]> results = new ArrayList<>();
        results.add(new Object[]{"Brand 1", 5L});
        results.add(new Object[]{"Brand 2", 8L});
        results.add(new Object[]{"Brand 3", 3L});
        when(productRepository.getCategoryCountsByBrand()).thenReturn(results);

        // Call the service method
        Map<String, Long> categoryCountsByBrand = productService.getCategoryCountsByBrand();

        // Verify the result
        assertEquals(3, categoryCountsByBrand.size());
        assertEquals(5L, categoryCountsByBrand.get("Brand 1"));
        assertEquals(8L, categoryCountsByBrand.get("Brand 2"));
        assertEquals(3L, categoryCountsByBrand.get("Brand 3"));
    }

    @Test
    public void testGetBrandCountsByCategory() {
        // Create mock data for the repository
        List<Object[]> results = new ArrayList<>();
        results.add(new Object[]{"Category 1", 5L});
        results.add(new Object[]{"Category 2", 8L});
        results.add(new Object[]{"Category 3", 3L});
        when(productRepository.getBrandCountsByCategory()).thenReturn(results);

        // Call the service method
        Map<String, Long> brandCountsByCategory = productService.getBrandCountsByCategory();

        // Verify the result
        assertEquals(3, brandCountsByCategory.size());
        assertEquals(5L, brandCountsByCategory.get("Category 1"));
        assertEquals(8L, brandCountsByCategory.get("Category 2"));
        assertEquals(3L, brandCountsByCategory.get("Category 3"));
    }

}
