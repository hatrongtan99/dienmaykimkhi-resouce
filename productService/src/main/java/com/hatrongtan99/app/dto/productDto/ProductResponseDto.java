package com.hatrongtan99.app.dto.productDto;

import com.hatrongtan99.app.entity.ProductEntity;

public record ProductResponseDto(
        Long id,
        String name,
        String slug
) {
    public static ProductResponseDto mapToDto(ProductEntity product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getSlug()
        );
    }
}
