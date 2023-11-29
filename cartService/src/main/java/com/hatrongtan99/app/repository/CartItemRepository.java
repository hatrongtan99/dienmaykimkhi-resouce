package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
