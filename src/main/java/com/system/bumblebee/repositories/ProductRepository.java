package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Declare the repository as a Spring component
@Repository
// Define the ProductRepository interface that extends JpaRepository and specifies the entity and primary key type
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Declare a method to save a product entity to the database
    ProductEntity save(ProductEntity product);

    // Declare a query to retrieve the count of products by brand
    @Query("SELECT productBrandName, COUNT(*) AS productCount FROM ProductEntity GROUP BY productBrandName")
    List<Object[]> getProductCountsByBrand();

    // Declare a query to retrieve the count of categories by brand
    @Query("SELECT productBrandName, COUNT(DISTINCT productCategoryName) AS categoryCount FROM ProductEntity GROUP BY productBrandName")
    List<Object[]> getCategoryCountsByBrand();

    // Declare a method to delete products by brand name
    int deleteByProductBrandName(String brandName);

    // Declare a query to retrieve the count of products by category
    @Query("SELECT productCategoryName, COUNT(*) AS productCount FROM ProductEntity GROUP BY productCategoryName")
    List<Object[]> getProductCountsByCategory();

    // Declare a query to retrieve the count of brands by category
    @Query("SELECT productCategoryName, COUNT(DISTINCT productBrandName) AS brandCount FROM ProductEntity GROUP BY productCategoryName")
    List<Object[]> getBrandCountsByCategory();

    // Declare a method to delete products by category name
    int deleteByProductCategoryName(String categoryName);

    // Declare a method to retrieve all products
    List<ProductEntity> findAll();

    // Declare a method to delete a product by its ID
    void deleteById(Long id);

    // Declare a method to check if a product with a given ID exists
    boolean existsById(Long id);

    // Declare a query to update product brand names
    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.productBrandName = :newBrandName WHERE p.productBrandName = :currentBrandName")
    int updateBrandNames(@Param("currentBrandName") String currentBrandName, @Param("newBrandName") String newBrandName);

    // Declare a query to update product category names
    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.productCategoryName = :newCategoryName WHERE p.productCategoryName = :currentCategoryName")
    int updateCategoryNames(String currentCategoryName, String newCategoryName);
}
