package com.hatrongtan99.app.dto.categoryDto;

public record CategoryResponseDto(
        Long id,
        String name,
        String slug,
        String description,
        Long thumbnail,
        Long parentId,
        boolean isActive
) {
}
