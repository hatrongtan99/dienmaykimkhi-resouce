package com.hatrongtan99.app.dto;

import jakarta.validation.constraints.NotBlank;

public record StockUpdateWhenOrderDto(
        @NotBlank
        int quantity
) {
}
