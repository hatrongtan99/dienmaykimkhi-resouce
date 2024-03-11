package com.hatrongtan99.saga.dto.inventoryProduct;

public record StockAdjustQuantityDto(
        Long productId,
        int adjustedQuantity,

        String note
) {
}
