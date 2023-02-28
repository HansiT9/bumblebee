package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByEmail(String email);
    AdminEntity save(AdminEntity admin);
}
