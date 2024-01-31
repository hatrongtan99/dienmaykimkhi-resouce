package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.PromotionProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

public interface PromotionProductRepository extends JpaRepository<PromotionProductEntity, Long> {

    @Query(value = """
            FROM PromotionProductEntity AS p WHERE p.productId = :productId
            AND p.startDate <= :date
            AND p.endDate >= :date
            AND p.isActive = TRUE
             """)
    Optional<PromotionProductEntity> findPromotionProduct(@Param("productId") Long productId, @Param("date") ZonedDateTime date);

    Optional<PromotionProductEntity> findByProductIdAndIsActiveIsTrue(Long productId);
}
