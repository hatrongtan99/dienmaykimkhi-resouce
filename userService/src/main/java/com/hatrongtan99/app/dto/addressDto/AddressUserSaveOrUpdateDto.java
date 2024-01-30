package com.hatrongtan99.app.dto.addressDto;

import jakarta.validation.constraints.NotBlank;

public record AddressUserSaveOrUpdateDto(
        @NotBlank
        String fullName,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String addressLine1,
        @NotBlank
        String addressLine2,
        @NotBlank
        String addressLine3
) {
}
