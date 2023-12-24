package com.hatrongtan99.app.dto.categoryDto;

public record CategoryUpdateDto(
        String name,
        String slug,
        String description,
        Long thumbnailId,
        Long parentId
) {
}
