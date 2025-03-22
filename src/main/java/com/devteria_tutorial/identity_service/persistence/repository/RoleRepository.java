package com.devteria_tutorial.identity_service.persistence.repository;

import com.devteria_tutorial.identity_service.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
