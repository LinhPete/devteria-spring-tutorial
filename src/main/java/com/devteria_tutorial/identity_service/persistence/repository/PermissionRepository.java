package com.devteria_tutorial.identity_service.persistence.repository;

import com.devteria_tutorial.identity_service.persistence.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
