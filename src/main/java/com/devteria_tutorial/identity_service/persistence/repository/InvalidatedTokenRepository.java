package com.devteria_tutorial.identity_service.persistence.repository;

import com.devteria_tutorial.identity_service.persistence.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
