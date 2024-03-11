package com.hatrongtan99.sagaservice.ochestration.dto.inventoryDto;

public record StockAdjustQuantityDto(
        Long productId,
        int adjustedQuantity,

        String note
) {
}
