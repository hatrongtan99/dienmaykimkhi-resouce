package com.hatrongtan99.app.dto;

import jakarta.validation.constraints.NotNull;

public record UserLoginRequestDto(
        @NotNull
        String username,

        @NotNull
        String password
) {
}
