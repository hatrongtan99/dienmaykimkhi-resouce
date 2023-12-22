package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.ProductAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductAttributeRepository extends JpaRepository<ProductAttributeEntity, Long> {
    Optional<ProductAttributeEntity> findByName(String name);
}
