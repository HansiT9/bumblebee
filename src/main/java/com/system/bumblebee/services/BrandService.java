package com.system.bumblebee.services;

import com.system.bumblebee.dto.Brand;

public interface BrandService {
    boolean checkBrandNameExists(String brandName);
    boolean saveBrand(Brand brand);
}
