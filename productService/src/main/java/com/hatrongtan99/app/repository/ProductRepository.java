package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
