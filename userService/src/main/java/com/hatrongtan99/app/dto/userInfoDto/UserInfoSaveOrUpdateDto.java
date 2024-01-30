package com.hatrongtan99.app.dto.userInfoDto;

import jakarta.validation.constraints.NotBlank;

public record UserInfoSaveOrUpdateDto(
        String avatarUrl,
        @NotBlank
        String fullName,
        @NotBlank
        String email,
        String numberPhone
) {
}
