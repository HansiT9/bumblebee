package com.system.bumblebee.services;

import com.system.bumblebee.dto.Brand;
import com.system.bumblebee.entity.BrandEntity;

import java.util.List;

public interface BrandService {
    boolean checkBrandNameExists(String brandName);
    boolean saveBrand(Brand brand);
    List<BrandEntity> fetchAllBrands();
    boolean removeBrand(int id);
    boolean updateBrandNameById(Long id, Brand brand);
}
