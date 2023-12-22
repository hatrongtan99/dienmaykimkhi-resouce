package com.hatrongtan99.app.dto.productAttributeDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttributeSaveDto(
        @NotNull
        @NotBlank
        String name
) {
}
