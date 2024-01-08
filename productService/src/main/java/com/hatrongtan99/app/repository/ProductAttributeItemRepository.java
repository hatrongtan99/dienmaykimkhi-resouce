package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.ProductAttributeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductAttributeItemRepository extends JpaRepository<ProductAttributeItemEntity, Long> {

    @Query(value = """
    FROM ProductAttributeItemEntity AS ai WHERE ai.productId.id = :productId
    """)
    List<ProductAttributeItemEntity> findByProductId(@Param("productId") Long productId);
}
