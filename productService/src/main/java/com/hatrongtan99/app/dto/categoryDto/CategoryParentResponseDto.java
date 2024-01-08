package com.hatrongtan99.app.dto.categoryDto;

import com.hatrongtan99.app.entity.CategoryEntity;

import java.util.List;

public record CategoryParentResponseDto(
        Long id,
        String name,
        String slug,
        String description,
        Long thumbnail,
        Long parentId,
        List<CategoryResponseDto> childCategories,
        boolean isActive
) {
    public static CategoryParentResponseDto mapToDto(CategoryEntity category) {
        return new CategoryParentResponseDto(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription(),
                category.getThumbnailId(),
                category.getParentId() == null ? null : category.getParentId().getId(),
                category.getCategories().stream().map((c) -> CategoryResponseDto.mapToDto(c, null)).toList(),
                category.isActive()
        );
    }
}
