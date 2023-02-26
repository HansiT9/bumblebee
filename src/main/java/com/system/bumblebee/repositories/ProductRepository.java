package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
}
