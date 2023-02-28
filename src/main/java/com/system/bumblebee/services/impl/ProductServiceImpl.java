package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Product;
import com.system.bumblebee.entity.ProductEntity;
import com.system.bumblebee.repositories.ProductRepository;
import com.system.bumblebee.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    // Injecting the ProductRepository
    @Autowired
    private ProductRepository productRepository;

    // Method to save a new product
    @Override
    public boolean saveProduct(Product product) {
        // Creating new ProductEntity object and setting its fields
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(product.getProductName());
        productEntity.setProductUrl(product.getProductUrl());
        productEntity.setProductBrandName(product.getProductBrandName());
        productEntity.setProductCategoryName(product.getProductCategoryName());
        productEntity.setProductQty(product.getProductQty());

        // Saving the productEntity to the database
        ProductEntity savedProduct = productRepository.save(productEntity);

        // Checking if productEntity is not null and returning a boolean value
        return savedProduct != null;
    }

    // Method to get the product counts by brand
    @Override
    public Map<String, Long> getProductCountsByBrand() {
        // Getting the product counts by brand from the database
        List<Object[]> results = productRepository.getProductCountsByBrand();
        Map<String, Long> productCounts = new HashMap<>();

        // Iterating over the result list and adding each brand name and its count to the productCounts map
        for (Object[] result : results) {
            String productBrandName = (String) result[0];
            Long productCount = (Long) result[1];
            productCounts.put(productBrandName, productCount);
        }

        // Returning the productCounts map
        return productCounts;
    }

    // Method to get the product counts by category
    @Override
    public Map<String, Long> getProductCountsByCategory() {
        // Getting the product counts by category from the database
        List<Object[]> results = productRepository.getProductCountsByCategory();
        Map<String, Long> productCounts = new HashMap<>();

        // Iterating over the result list and adding each category name and its count to the productCounts map
        for (Object[] result : results) {
            String productCategoryName = (String) result[0];
            Long productCount = (Long) result[1];
            productCounts.put(productCategoryName, productCount);
        }

        // Returning the productCounts map
        return productCounts;
    }

    // Method to get the category counts by brand
    @Override
    public Map<String, Long> getCategoryCountsByBrand() {
        // Getting the category counts by brand from the database
        List<Object[]> results = productRepository.getCategoryCountsByBrand();
        Map<String, Long> categoryCounts = new HashMap<>();

        // Iterating over the result list and adding each brand name and its count to the categoryCounts map
        for (Object[] result : results) {
            String productBrandName = (String) result[0];
            Long categoryCount = (Long) result[1];
            categoryCounts.put(productBrandName, categoryCount);
        }

        // Returning the categoryCounts map
        return categoryCounts;
    }

    // Method to get the brand counts by category
    @Override
    public Map<String, Long> getBrandCountsByCategory() {
        // Getting the brand counts by category from the database
        List<Object[]> results = productRepository.getBrandCountsByCategory();
        Map<String, Long> brandCounts = new HashMap<>();

        for (Object[] result : results) {
            String productCategoryName = (String) result[0];
            Long brandCount = (Long) result[1];
            brandCounts.put(productCategoryName, brandCount);
        }

        return brandCounts;
    }

    // This method deletes all the products with the given brand name by calling the deleteByProductBrandName method
    // of the productRepository interface. The @Transactional annotation is used to ensure that the operation is atomic
    // and all changes are committed to the database at once. The method returns true if the operation is successful and
    // false otherwise.
    @Override
    @Transactional
    public boolean deleteAllWithBrandName(String brandName) {
        int result = productRepository.deleteByProductBrandName(brandName);
        return result > 0;
    }

    // This method deletes all the products with the given category name by calling the deleteByProductCategoryName method
    // of the productRepository interface. The @Transactional annotation is used to ensure that the operation is atomic
    // and all changes are committed to the database at once. The method returns true if the operation is successful and
    // false otherwise.
    @Override
    @Transactional
    public boolean deleteAllWithCategoryName(String categoryName) {
        int result = productRepository.deleteByProductCategoryName(categoryName);
        return result > 0;
    }

    // This method retrieves all the products from the database by calling the findAll method of the productRepository
    // interface. The method returns a list of ProductEntity objects.
    @Override
    public List<ProductEntity> fetchAllProducts() {
        List<ProductEntity> products = productRepository.findAll();

        return products;
    }

    // This method receives an int value that represents the id of a product to be removed
    // The method deletes the product with the given id from the database using the deleteById() method of the product repository
    // The method then checks if the product still exists in the database using the existsById() method of the product repository
    // The method returns true if the product no longer exists in the database, false otherwise
    @Override
    public boolean removeProduct(int id) {
        productRepository.deleteById((long) id);
        boolean exist = productRepository.existsById((long) id);

        return exist;
    }


    // This method receives two String values representing the current and new brand names respectively
    // The method updates the brand name of all products with the given current brand name to the new brand name
    // The method returns true if at least one product's brand name was updated, false otherwise
    @Override
    @Transactional
    public boolean updateProductBrandNames(String currentBrandName, String newBrandName) {
        int updateBrandNames = productRepository.updateBrandNames(currentBrandName, newBrandName);

        return  updateBrandNames > 0;
    }

    // This method receives two String values representing the current and new category names respectively
    // The method updates the category name of all products with the given current category name to the new category name
    // The method returns true if at least one product's category name was updated, false otherwise
    @Override
    public boolean updateProductCategoryNames(String currentCategoryName, String newCategoryName) {
        int updateBrandNames = productRepository.updateCategoryNames(currentCategoryName, newCategoryName);

        return  updateBrandNames > 0;
    }

    // This method receives a Long value representing the id of a product to be updated, and a Product object containing the new product details
    // The method first checks if the product with the given id exists in the database using the findById() method of the product repository
    // If the product exists, the method updates its details using the setter methods of the ProductEntity class
    // The method then saves the updated product details to the database using the save() method of the product repository
    // The method returns true if the product was updated successfully, false otherwise
    @Override
    public boolean updateProductNameById(Long id, Product product) {
        Optional<ProductEntity> prductOptional = productRepository.findById(id);
        if (prductOptional.isPresent()) {
            ProductEntity productEntity = prductOptional.get();
            productEntity.setProductName(product.getProductName());
            productEntity.setProductUrl(product.getProductUrl());
            productEntity.setProductBrandName(product.getProductBrandName());
            productEntity.setProductCategoryName(product.getProductCategoryName());
            productEntity.setProductQty(product.getProductQty());

            ProductEntity savedProduct = productRepository.save(productEntity);
            return savedProduct != null;
        }
        return false;
    }
}
