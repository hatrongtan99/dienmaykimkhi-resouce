package com.hatrongtan99.app.dto.order;

import com.hatrongtan99.app.entity.OrderItemEntity;

public record OrderItemResponseEntity(
        Long id,
        String productName,
        Double productPrice,
        Integer quantity,
        Double discount
) {
    public static OrderItemResponseEntity mapToDto(OrderItemEntity entity) {
        return new OrderItemResponseEntity(
                entity.getId(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getQuantity(),
                entity.getDiscount()
        );
    }
}
