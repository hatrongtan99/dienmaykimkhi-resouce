package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
    void deleteByImageIdInAndProductId(Collection<Long> imageId, ProductEntity productId);
}
