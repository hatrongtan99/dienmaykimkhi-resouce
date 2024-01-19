package com.hatrongtan99.app.dto;

public record DetailStockDto(
        Long id,
        Long productId,
        Integer quantity,
        Integer quantitySold
) {
}
