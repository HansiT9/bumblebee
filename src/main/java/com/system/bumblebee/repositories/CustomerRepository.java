package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Defined a repository interface for CustomerEntity
@Repository // Spring Data annotation to mark the interface as a repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email); // method to find customer entity by email
    CustomerEntity save(CustomerEntity customer); // method to save customer entity
    List<CustomerEntity> findAll(); // method to find all customer entities
    Optional<CustomerEntity> findById(Long cusID); // method to find customer entity by id
    long count(); // JPA method to count the number of entities in the table
    CustomerEntity findByNic(String nic); // method to find customer entity by nic
}
