package com.hatrongtan99.paymentservice.dto;

public record OrderItemResponseDto(
        Long id,
        String productName,
        String thumbnailUrl,
        Double productPrice,
        Integer quantity,
        Double discount
) {
}
