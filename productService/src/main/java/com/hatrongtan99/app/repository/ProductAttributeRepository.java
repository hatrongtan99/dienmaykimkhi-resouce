package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.ProductAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductAttributeRepository extends JpaRepository<ProductAttributeEntity, Long> {
    Optional<ProductAttributeEntity> findByName(String name);
}
