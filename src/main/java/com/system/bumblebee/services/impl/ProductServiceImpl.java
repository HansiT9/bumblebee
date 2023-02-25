package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Product;
import com.system.bumblebee.entity.ProductEntity;
import com.system.bumblebee.repositories.ProductRepository;
import com.system.bumblebee.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
