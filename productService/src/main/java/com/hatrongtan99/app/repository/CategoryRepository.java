package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsBySlug(String slug);

    Optional<CategoryEntity> findBySlug(String slug);
}
