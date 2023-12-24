package com.hatrongtan99.app.dto.brandDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BrandSaveDto(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        String slug,

        @NotNull
        Long thumbnailId
) {
}
