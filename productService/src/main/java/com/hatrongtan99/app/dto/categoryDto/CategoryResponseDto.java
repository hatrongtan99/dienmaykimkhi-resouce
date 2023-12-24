package com.hatrongtan99.app.dto.categoryDto;

import com.hatrongtan99.app.entity.CategoryEntity;

public record CategoryResponseDto(
        Long id,
        String name,
        String slug,
        String description,
        Long thumbnail,
        Long parentId,
        boolean isActive
) {
    public static CategoryResponseDto mapToDto(CategoryEntity category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription(),
                category.getThumbnailId(),
                category.getParentId() == null ? null : category.getParentId().getId(),
                category.isActive()
        );
    }
}
