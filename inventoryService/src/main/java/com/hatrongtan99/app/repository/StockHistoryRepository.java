package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.StockHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockHistoryRepository extends JpaRepository<StockHistoryEntity, Long> {
}
