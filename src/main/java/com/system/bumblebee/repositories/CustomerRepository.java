package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
    CustomerEntity save(CustomerEntity customer);
}
