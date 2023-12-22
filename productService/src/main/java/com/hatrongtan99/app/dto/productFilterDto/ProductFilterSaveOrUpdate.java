package com.hatrongtan99.app.dto.productFilterDto;

import jakarta.validation.constraints.NotBlank;

public record ProductFilterSaveOrUpdate(
        Long id,
        @NotBlank
        String name,

        boolean isActive
) {
}
