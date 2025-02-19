package com.hatrongtan99.app.dto.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StockAdjustQuantityDto(
        @NotNull
        Long productId,
        @NotNull
        int adjustedQuantity,

        @NotBlank
        String note
) {
}
