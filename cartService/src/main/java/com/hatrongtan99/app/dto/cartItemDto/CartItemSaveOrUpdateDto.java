package com.hatrongtan99.app.dto.cartItemDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CartItemSaveOrUpdateDto(
        @NotNull
        Long productId,
        @NotNull
        int quantity
) {
}
