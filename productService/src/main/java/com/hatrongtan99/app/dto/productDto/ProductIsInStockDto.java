package com.hatrongtan99.app.dto.productDto;

public record ProductIsInStockDto(
        Long productId,
        boolean isInStock
) {
}
