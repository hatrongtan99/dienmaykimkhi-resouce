package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String name);
    Optional<UserEntity> findByProviderId(String providerId);

    Boolean existsByUsername(String username);
}
