package com.system.bumblebee.services;

import com.system.bumblebee.dto.Product;

import java.util.Map;

public interface ProductService {
    boolean saveProduct(Product product);
    Map<String, Long> getProductCountsByBrand();
    Map<String, Long> getCategoryCountsByBrand();
    boolean deleteAllWithBrandName(String brandName);
    Map<String, Long> getProductCountsByCategory();
    Map<String, Long> getBrandCountsByCategory();
}
