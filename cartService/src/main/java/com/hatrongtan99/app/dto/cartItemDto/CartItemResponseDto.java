package com.hatrongtan99.app.dto.cartItemDto;

import com.hatrongtan99.app.entity.CartItemEntity;

import java.util.Date;

public record CartItemResponseDto(
        Long id,
        Long productId,
        int quantity,
        boolean isActive,
        Date createAt,
        Date updateAt
) {
    public static CartItemResponseDto mapToDto(CartItemEntity item) {
        return new CartItemResponseDto(
                item.getId(),
                item.getProductId(),
                item.getQuantity(),
                item.isActive(),
                item.getCreateAt(),
                item.getLastModifyAt()
        );
    }
}
