package com.hatrongtan99.app.dto.brandDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BrandUpdateDto(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        String slug,
        Long brandThumbnail
) {
}
