package com.hatrongtan99.app.dto.cartDto;

public record CartItemDto(
        Long id,
        Long productId,
        String productName,
        String thumbnailUrl,
        Integer quantity,
        Double price,
        Double discount
) {
}
