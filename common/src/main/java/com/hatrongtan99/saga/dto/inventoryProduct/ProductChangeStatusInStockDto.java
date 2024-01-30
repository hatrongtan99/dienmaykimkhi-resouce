package com.hatrongtan99.sagaservice.choreography.inventoryProduct.dto;

public record ProductChangeStatusInStockDto(
        Long productId,
        boolean isInStock
) {
}
