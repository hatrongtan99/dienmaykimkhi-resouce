package com.hatrongtan99.app.dto.order;

public record OrderItemRequestDto(
        Long productId,
        String productName,
        String thumbnailUrl,
        Integer quantity,
        Double price,
        Integer discount
) {
}
