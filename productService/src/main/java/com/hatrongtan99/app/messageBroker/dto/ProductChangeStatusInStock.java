package com.hatrongtan99.app.messageBroker.dto;

public record ProductChangeStatusInStock(
        Long productId,
        boolean isInStock
) {
}
