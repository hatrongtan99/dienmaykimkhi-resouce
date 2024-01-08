package com.hatrongtan99.app.dto.brandDto;

import com.hatrongtan99.app.dto.mediaDto.MediaResponseDto;
import com.hatrongtan99.app.dto.mediaDto.ThumbnailResponseDto;
import com.hatrongtan99.app.entity.BrandEntity;
import lombok.Builder;

public record BrandResponseDto(
        Long id,
        String name,
        ThumbnailResponseDto thumbnail,
        String slug,
        boolean isActive
) {
    public static BrandResponseDto mapToDto(BrandEntity brand, MediaResponseDto media) {
        ThumbnailResponseDto thumbnail = new ThumbnailResponseDto(media.id(), media.url());
        return new BrandResponseDto(
                brand.getId(),
                brand.getName(),
                thumbnail,
                brand.getSlug(),
                brand.isActive()
        );
    }

    public static BrandResponseDto mapToDto(BrandEntity brand) {
        return new BrandResponseDto(
                brand.getId(),
                brand.getName(),
                null,
                brand.getSlug(),
                brand.isActive()
        );
    }
}
