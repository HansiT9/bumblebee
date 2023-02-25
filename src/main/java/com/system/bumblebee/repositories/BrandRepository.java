package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByBrandName(String brandName);
    BrandEntity save(BrandEntity brand);
}
