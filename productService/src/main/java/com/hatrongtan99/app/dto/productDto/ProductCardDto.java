package com.hatrongtan99.app.dto.productDto;

import com.hatrongtan99.app.dto.brandDto.BrandCardDto;
import com.hatrongtan99.app.entity.BrandEntity;
import com.hatrongtan99.app.entity.ProductEntity;

public record ProductCardDto(
        Long id,
        String name,
        String slug,
        double price,
        BrandCardDto brand
) {
    public static ProductCardDto mapToDto(ProductEntity product, double price) {
        BrandEntity brandEntity = product.getBrandId();
        BrandCardDto brand = null;
        if (brandEntity != null) {
            brand = new BrandCardDto(brandEntity.getId(), brandEntity.getName(), brandEntity.getSlug(), null);
        }
        return new ProductCardDto(
                product.getId(),
                product.getName(),
                product.getSlug(),
                price,
                brand
        );
    }
}
