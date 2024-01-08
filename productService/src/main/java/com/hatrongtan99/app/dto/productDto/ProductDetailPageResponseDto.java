package com.hatrongtan99.app.dto.productDto;

import com.hatrongtan99.app.dto.productMetadataDto.ProductMetadataResponseDto;
import com.hatrongtan99.app.entity.CategoryEntity;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.entity.ProductImageEntity;

import java.util.List;

public record ProductDetailPageResponseDto(
        Long id,
        String name,
        String slug,
        List<Long> images,
        Long descriptionId,
        String sku,
        String guarantee,
        double price,
        Long thumbnailId,
        Long brandId,
        List<Long> categories,
        List<Long> productRelate,
        List<ProductMetadataResponseDto> metadata,
        boolean isAvailInStock,
        boolean isActive
) {
    public static ProductDetailPageResponseDto mapToDto(ProductEntity product) {
        double price = product.getPrice().get(0) != null ? product.getPrice().get(0).getPrice() : 0;
        return new ProductDetailPageResponseDto(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getImages().stream().map(ProductImageEntity::getId).toList(),
                product.getDescriptionId().getId(),
                product.getSku(),
                product.getGuarantee(),
                price,
                product.getThumbnailId(),
                product.getBrandId() == null ? null : product.getBrandId().getId(),
                product.getCategories().stream().map(CategoryEntity::getId).toList(),
                product.getProductRelate().stream().map(ProductEntity::getId).toList(),
                product.getMetadata().stream().map(m -> new ProductMetadataResponseDto(m.getId(), m.getName(), m.getValue())).toList(),
                product.isAvailInStock(),
                product.isActive()
        );
    }
}
