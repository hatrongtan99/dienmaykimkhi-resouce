package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
