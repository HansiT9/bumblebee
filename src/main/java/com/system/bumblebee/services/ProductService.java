package com.system.bumblebee.services;

import com.system.bumblebee.dto.Product;
import com.system.bumblebee.entity.ProductEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    boolean saveProduct(Product product);
    Map<String, Long> getProductCountsByBrand();
    Map<String, Long> getCategoryCountsByBrand();
    boolean deleteAllWithBrandName(String brandName);
    Map<String, Long> getProductCountsByCategory();
    Map<String, Long> getBrandCountsByCategory();
    boolean deleteAllWithCategoryName(String categoryName);
    List<ProductEntity> fetchAllProducts();
    boolean removeProduct(int id);
    boolean updateProductBrandNames(String currentBrandName, String newBrandName);
    boolean updateProductCategoryNames(String currentCategoryName, String newCategoryName);
}
