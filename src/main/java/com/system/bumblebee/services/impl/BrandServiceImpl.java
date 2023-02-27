package com.system.bumblebee.services.impl;

import com.system.bumblebee.dto.Brand;
import com.system.bumblebee.entity.BrandEntity;
import com.system.bumblebee.repositories.BrandRepository;
import com.system.bumblebee.services.BrandService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public boolean checkBrandNameExists(String brandName) {
        Optional<BrandEntity> brand = brandRepository.findByBrandName(brandName);

        return brand.isPresent();
    }

    @Override
    public boolean saveBrand(Brand brand) {
        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setBrandName(brand.getBrandName());
        brandEntity.setBrandLogo(brand.getBrandLogo());

        BrandEntity savedBrand = brandRepository.save(brandEntity);
        return savedBrand != null;
    }

    @Override
    public List<BrandEntity> fetchAllBrands() {
        List<BrandEntity> brands = brandRepository.findAll();

        return brands;
    }

    @Override
    public boolean removeBrand(int id) {
        brandRepository.deleteById((long) id);
        boolean exist = brandRepository.existsById((long) id);

        return exist;
    }

    @Override
    @Transactional
    public boolean updateBrandNameById(Long id, Brand brand) {
        Optional<BrandEntity> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            BrandEntity brandEntity = brandOptional.get();
            brandEntity.setBrandName(brand.getBrandName());
            brandEntity.setBrandLogo(brand.getBrandLogo());

            BrandEntity savedBrand = brandRepository.save(brandEntity);
            return savedBrand != null;
        }
        return false;
    }
}
