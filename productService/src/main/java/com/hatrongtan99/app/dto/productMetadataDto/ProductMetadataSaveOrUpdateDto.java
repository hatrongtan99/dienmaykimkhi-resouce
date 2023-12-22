package com.hatrongtan99.app.dto.productMetadataDto;

import jakarta.validation.constraints.NotBlank;

public record ProductMetadataSaveOrUpdateDto(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String value
) {
}
