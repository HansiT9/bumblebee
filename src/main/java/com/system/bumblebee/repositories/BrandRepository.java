package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Defined a repository interface for BrandEntity
@Repository // Spring Data annotation to mark the interface as a repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByBrandName(String brandName); // method to find brand entity by brand name
    BrandEntity save(BrandEntity brand); // method to save a brand entity
    List<BrandEntity> findAll(); // method to find get all brand entity's
    void deleteById(Long id); // method to delete by id
    boolean existsById(Long id); // method to check if exist by id
    Optional<BrandEntity> findById(Long id); // method to find brand entity by id
}
