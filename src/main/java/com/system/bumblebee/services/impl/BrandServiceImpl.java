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

    // Injecting the BrandRepository dependency
    @Autowired
    private BrandRepository brandRepository;

    // method checks if a brand name exists in the database.
    @Override
    public boolean checkBrandNameExists(String brandName) {
        Optional<BrandEntity> brand = brandRepository.findByBrandName(brandName);

        return brand.isPresent();
    }

    // method saves a new brand in the database.
    @Override
    public boolean saveBrand(Brand brand) {
        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setBrandName(brand.getBrandName());
        brandEntity.setBrandLogo(brand.getBrandLogo());

        BrandEntity savedBrand = brandRepository.save(brandEntity);
        return savedBrand != null;
    }

    // method fetches all brands from the database.
    @Override
    public List<BrandEntity> fetchAllBrands() {
        List<BrandEntity> brands = brandRepository.findAll();

        return brands;
    }

    // method removes a brand from the database.
    @Override
    public boolean removeBrand(int id) {
        // Deleting the brand with the provided id
        brandRepository.deleteById((long) id);

        // Checking if the brand still exists in the database
        boolean exist = brandRepository.existsById((long) id);

        return exist;
    }

    // method updates the name and logo of a brand in the database.
    @Override
    @Transactional
    public boolean updateBrandNameById(Long id, Brand brand) {
        Optional<BrandEntity> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            // Updating the BrandEntity object with the provided data
            BrandEntity brandEntity = brandOptional.get();
            brandEntity.setBrandName(brand.getBrandName());
            brandEntity.setBrandLogo(brand.getBrandLogo());

            // Saving the updated brand in the database
            BrandEntity savedBrand = brandRepository.save(brandEntity);
            return savedBrand != null;
        }
        return false;
    }

    @Override
    public long getBrandCount() {
        return brandRepository.count();
    }
}
