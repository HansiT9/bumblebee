package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity save(ProductEntity product);
    @Query("SELECT productBrandName, COUNT(*) AS productCount FROM ProductEntity GROUP BY productBrandName")
    List<Object[]> getProductCountsByBrand();
    @Query("SELECT productBrandName, COUNT(DISTINCT productCategoryName) AS categoryCount FROM ProductEntity GROUP BY productBrandName")
    List<Object[]> getCategoryCountsByBrand();
    int deleteByProductBrandName(String brandName);
    @Query("SELECT productCategoryName, COUNT(*) AS productCount FROM ProductEntity GROUP BY productCategoryName")
    List<Object[]> getProductCountsByCategory();
    @Query("SELECT productCategoryName, COUNT(DISTINCT productBrandName) AS brandCount FROM ProductEntity GROUP BY productCategoryName")
    List<Object[]> getBrandCountsByCategory();
    int deleteByProductCategoryName(String categoryName);
    List<ProductEntity> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.productBrandName = :newBrandName WHERE p.productBrandName = :currentBrandName")
    int updateBrandNames(@Param("currentBrandName") String currentBrandName, @Param("newBrandName") String newBrandName);
    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.productCategoryName = :newCategoryName WHERE p.productCategoryName = :currentCategoryName")
    int updateCategoryNames(String currentCategoryName, String newCategoryName);
}
