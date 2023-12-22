package com.hatrongtan99.app.dto.productAttributeDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttributeUpdateDto(
        @NotBlank
        @NotNull
        String name
) {
}
