package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.PriceEntity;
import com.hatrongtan99.app.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long>, JpaSpecificationExecutor<PriceEntity> {
    PriceEntity findByProductIdAndIsActiveIsTrue(ProductEntity productId);
}
