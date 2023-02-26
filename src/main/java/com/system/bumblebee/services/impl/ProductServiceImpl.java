package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Product;
import com.system.bumblebee.entity.ProductEntity;
import com.system.bumblebee.repositories.ProductRepository;
import com.system.bumblebee.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean saveProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductName(product.getProductName());
        productEntity.setProductUrl(product.getProductUrl());
        productEntity.setProductBrandName(product.getProductBrandName());
        productEntity.setProductCategoryName(product.getProductCategoryName());
        productEntity.setProductQty(product.getProductQty());

        ProductEntity savedProduct = productRepository.save(productEntity);
        return savedProduct != null;
    }

    @Override
    public Map<String, Long> getProductCountsByBrand() {
        List<Object[]> results = productRepository.getProductCountsByBrand();
        Map<String, Long> productCounts = new HashMap<>();

        for (Object[] result : results) {
            String productBrandName = (String) result[0];
            Long productCount = (Long) result[1];
            productCounts.put(productBrandName, productCount);
        }

        return productCounts;
    }

    @Override
    public Map<String, Long> getCategoryCountsByBrand() {
        List<Object[]> results = productRepository.getCategoryCountsByBrand();
        Map<String, Long> categoryCounts = new HashMap<>();

        for (Object[] result : results) {
            String productBrandName = (String) result[0];
            Long categoryCount = (Long) result[1];
            categoryCounts.put(productBrandName, categoryCount);
        }

        return categoryCounts;
    }

}
