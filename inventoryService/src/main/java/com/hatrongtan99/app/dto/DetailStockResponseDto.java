package com.hatrongtan99.app.dto;

import com.hatrongtan99.app.entity.StockEntity;

public record DetailStockResponseDto(
        Long id,
        Long productId,
        Integer quantity,
        Integer quantitySold
) {
    public static DetailStockResponseDto mapToDto(StockEntity entity) {
        return new DetailStockResponseDto(
                entity.getId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getQuantitySold()
        );
    }
}
