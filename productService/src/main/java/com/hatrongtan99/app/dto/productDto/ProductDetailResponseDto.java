package com.hatrongtan99.app.dto.productDto;

import com.hatrongtan99.app.entity.ProductEntity;

public record ProductDetailResponseDto(
        Long id,
        String name,
        String slug
) {
    public static ProductDetailResponseDto mapToDto(ProductEntity product) {
        return new ProductDetailResponseDto(
                product.getId(),
                product.getName(),
                product.getSlug()
        );
    }
}
