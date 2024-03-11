package com.hatrongtan99.app.dto.order;

import com.hatrongtan99.app.entity.OrderItemEntity;

public record OrderItemResponseDto(
        Long id,
        String productName,
        String thumbnailUrl,
        Double productPrice,
        Integer quantity,
        Integer discount
) {
    public static OrderItemResponseDto mapToDto(OrderItemEntity entity) {
        return new OrderItemResponseDto(
                entity.getId(),
                entity.getProductName(),
                entity.getThumbnailUrl(),
                entity.getProductPrice(),
                entity.getQuantity(),
                entity.getDiscount()
        );
    }
}
