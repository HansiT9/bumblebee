package com.system.bumblebee.service;

import com.system.bumblebee.dto.Brand;
import com.system.bumblebee.entity.BrandEntity;
import com.system.bumblebee.repositories.BrandRepository;
import com.system.bumblebee.services.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @InjectMocks
    BrandServiceImpl brandService;

    @Mock
    BrandRepository brandRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test checkBrandNameExists returns true when brand name exists")
    void testCheckBrandNameExists() {
        String brandName = "Test Brand";
        Optional<BrandEntity> optionalBrandEntity = Optional.of(new BrandEntity());
        when(brandRepository.findByBrandName(brandName)).thenReturn(optionalBrandEntity);

        boolean result = brandService.checkBrandNameExists(brandName);

        assertTrue(result);
        verify(brandRepository, times(1)).findByBrandName(brandName);
    }

    @Test
    @DisplayName("Test checkBrandNameExists returns false when brand name does not exist")
    void testCheckBrandNameDoesNotExist() {
        String brandName = "Test Brand";
        when(brandRepository.findByBrandName(brandName)).thenReturn(Optional.empty());

        boolean result = brandService.checkBrandNameExists(brandName);

        assertFalse(result);
        verify(brandRepository, times(1)).findByBrandName(brandName);
    }

    @Test
    @DisplayName("Test saveBrand returns true when brand is saved")
    void testSaveBrand() {
        Brand brand = new Brand();
        brand.setBrandName("Test Brand");
        brand.setBrandLogo("test.png");

        when(brandRepository.save(any(BrandEntity.class))).thenReturn(new BrandEntity());

        boolean result = brandService.saveBrand(brand);

        assertTrue(result);
        verify(brandRepository, times(1)).save(any(BrandEntity.class));
    }

    @Test
    @DisplayName("Test fetchAllBrands returns list of brands")
    void testFetchAllBrands() {
        List<BrandEntity> brands = new ArrayList<>();
        brands.add(new BrandEntity());
        when(brandRepository.findAll()).thenReturn(brands);

        List<BrandEntity> result = brandService.fetchAllBrands();

        assertEquals(brands, result);
        verify(brandRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test removeBrand returns true when brand is removed")
    void testRemoveBrand() {
        int id = 1;

        doNothing().when(brandRepository).deleteById((long) id);
        when(brandRepository.existsById((long) id)).thenReturn(false);

        boolean result = brandService.removeBrand(id);

        assertFalse(result);
        verify(brandRepository, times(1)).deleteById((long) id);
        verify(brandRepository, times(1)).existsById((long) id);
    }

    @Test
    public void testSaveBrand_Success() {
        // Setup
        Brand brand = new Brand();
        brand.setBrandName("Nike");
        brand.setBrandLogo("https://static.nike.com/a/images/f_jpg,q_auto:eco/61b4738b-e1e1-4786-8f6c-26aa0008e80b/swoosh-logo-black.png");

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setBrandName(brand.getBrandName());
        brandEntity.setBrandLogo(brand.getBrandLogo());

        Mockito.when(brandRepository.save(Mockito.any(BrandEntity.class))).thenReturn(brandEntity);

        // Execute
        boolean result = brandService.saveBrand(brand);

        // Assert
        assertTrue(result);
        Mockito.verify(brandRepository, Mockito.times(1)).save(Mockito.any(BrandEntity.class));
    }

    @Test
    @DisplayName("Fetch all brands - Success")
    public void testFetchAllBrands_Success() {
        // Setup
        BrandEntity brand1 = new BrandEntity();
        brand1.setId(1L);
        brand1.setBrandName("Brand1");
        brand1.setBrandLogo("Logo1");

        BrandEntity brand2 = new BrandEntity();
        brand2.setId(2L);
        brand2.setBrandName("Brand2");
        brand2.setBrandLogo("Logo2");

        List<BrandEntity> brands = Arrays.asList(brand1, brand2);

        when(brandRepository.findAll()).thenReturn(brands);

        // Execution
        List<BrandEntity> result = brandService.fetchAllBrands();

        // Verification
        assertEquals(brands, result);
        verify(brandRepository, times(1)).findAll();
    }
}
