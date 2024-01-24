package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.StockHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistoryEntity, Long> {
    @Query(
            value = """
                        FROM StockHistoryEntity AS his LEFT JOIN his.stockId AS s WHERE s.productId = :productId
                    """
    )
    Page<StockHistoryEntity> findByProductId(@Param("productId") Long productId, Pageable pageable);
}
