package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
