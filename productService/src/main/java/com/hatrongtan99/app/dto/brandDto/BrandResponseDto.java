package com.hatrongtan99.app.dto.brandDto;

import com.hatrongtan99.app.entity.BrandEntity;
import lombok.Builder;

@Builder
public record BrandResponseDto(
        Long id,
        String name,
        Long thumbnailId,
        String slug,
        boolean isActive
) {
    public static BrandResponseDto mapToDto(BrandEntity brand) {
        return BrandResponseDto.builder()
                .id(brand.getId())
                .thumbnailId(brand.getThumbnailId())
                .name(brand.getName())
                .slug(brand.getSlug())
                .isActive(brand.isActive())
                .build();
    }
}
