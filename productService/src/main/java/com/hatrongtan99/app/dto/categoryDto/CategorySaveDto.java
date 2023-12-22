package com.hatrongtan99.app.dto.categoryDto;

import com.hatrongtan99.app.dto.mediaDto.MediaSaveDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategorySaveDto(
        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String slug,
        String description,
        @NotNull
        MediaSaveDto thumbnail,
        Long parentId
) {
}
