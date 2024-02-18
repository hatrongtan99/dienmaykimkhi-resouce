package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.PromotionEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PromotionEntity> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PromotionEntity> findByCouponCodeAndIsActiveIsTrue(String couponCode);

    boolean existsByCouponCode(String couponCode);
}
