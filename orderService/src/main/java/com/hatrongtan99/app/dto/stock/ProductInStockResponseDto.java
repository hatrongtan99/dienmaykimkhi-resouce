package com.hatrongtan99.app.dto.stock;

public record ProductInStockResponseDto(
        Long id,
        Long productId,
        Integer quantity
) {
}
