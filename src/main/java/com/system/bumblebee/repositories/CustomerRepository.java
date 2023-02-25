package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
    CustomerEntity save(CustomerEntity customer);
    List<CustomerEntity> findAll();
    Optional<CustomerEntity> findById(Long cusID);
}
