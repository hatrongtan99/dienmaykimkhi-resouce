package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsBySlug(String slug);

    Optional<BrandEntity> findBySlug(String slug);
}
