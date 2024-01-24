package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.StockEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<StockEntity> findByProductId(Long id);
}
