package com.hatrongtan99.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StockAdjustQuantityDto(
    @NotNull
    int adjustedQuantity,

    @NotBlank
    String note
) {
}
