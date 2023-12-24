package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    @Query(
            value = """
                       FROM ProductEntity p LEFT JOIN p.brandId b LEFT JOIN p.price pr
                        WHERE b.slug = :brandSlug AND b.isActive = TRUE AND pr.isActive = TRUE
                    """
    )
    Page<ProductEntity> findByBrandSlugWithPriceActive(@Param("brandSlug") String brandSlug, Pageable pageable);
    @Query(
            value = """
                    FROM ProductEntity p LEFT JOIN p.price pr WHERE p.id IN :ids AND pr.isActive = TRUE
                    """
    )
    Page<ProductEntity> findByIdIn(@Param("ids") List<Long> ids, Pageable pageable);

    Optional<ProductEntity> findBySlugAndIsActiveIsTrue(String slug);

    boolean existsBySlug(String slug);

    boolean existsBySku(String sku);

}
