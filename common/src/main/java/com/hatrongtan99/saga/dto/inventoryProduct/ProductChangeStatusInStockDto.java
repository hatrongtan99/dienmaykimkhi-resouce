package com.hatrongtan99.saga.dto.inventoryProduct;

public record ProductChangeStatusInStockDto(
        Long productId,
        boolean isInStock
) {
}
