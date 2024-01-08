package com.hatrongtan99.app.dto.ProductAttributeItemDto;

import jakarta.validation.constraints.NotBlank;

public record ProductAttributeItemSaveDto(
        @NotBlank
        String name,
        @NotBlank
        String value
) {
}
