package com.hatrongtan99.app.dto.categoryDto;

import com.hatrongtan99.app.dto.mediaDto.MediaSaveDto;

public record CategoryUpdateDto(
        String name,
        String slug,
        String description,
        MediaSaveDto thumbnail,
        Long parentId
) {
}
