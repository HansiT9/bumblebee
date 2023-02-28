package com.system.bumblebee.repositories;

import com.system.bumblebee.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Defined a repository interface for AdminEntity
@Repository // Spring Data annotation to mark the interface as a repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByEmail(String email); // method to find an admin entity by email
    AdminEntity save(AdminEntity admin); // method to save an admin entity
}
