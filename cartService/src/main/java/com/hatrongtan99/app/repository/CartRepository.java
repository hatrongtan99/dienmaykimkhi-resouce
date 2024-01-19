package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.CartEntity;
import com.hatrongtan99.enumeration.cart.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByUserIdAndStatus(Long userId, CartStatus status);
}
