package com.hatrongtan99.app.dto.categoryDto;

import com.hatrongtan99.app.dto.mediaDto.MediaResponseDto;
import com.hatrongtan99.app.dto.mediaDto.ThumbnailResponseDto;
import com.hatrongtan99.app.entity.CategoryEntity;

public record CategoryResponseDto(
        Long id,
        String name,
        String slug,
        String description,
        ThumbnailResponseDto thumbnail,
        Long parentId,
        boolean hasChild,
        boolean isActive
) {
    public static CategoryResponseDto mapToDto(CategoryEntity category, MediaResponseDto media) {
        ThumbnailResponseDto thumbnail = null;
        if (media != null) {
            thumbnail = new ThumbnailResponseDto(media.id(), media.url());
        }

        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription(),
                thumbnail,
                category.getParentId() == null ? null : category.getParentId().getId(),
                category.isHasChild(),
                category.isActive()
        );
    }
}
