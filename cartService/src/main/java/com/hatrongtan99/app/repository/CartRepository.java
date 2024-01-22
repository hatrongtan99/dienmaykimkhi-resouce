package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.CartEntity;
import com.hatrongtan99.enumeration.cart.CartStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<CartEntity> findByUserIdAndStatus(Long userId, CartStatus status);
}
