package com.hatrongtan99.app.dto.brandDto;

import lombok.Builder;

@Builder
public record BrandResponseDto(
        Long id,
        String name,
        Long thumbnailId,
        String slug,
        boolean isActive
) {
}
