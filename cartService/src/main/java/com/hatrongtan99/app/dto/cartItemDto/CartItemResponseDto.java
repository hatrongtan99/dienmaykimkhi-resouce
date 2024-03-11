package com.hatrongtan99.app.dto.cartItemDto;


import java.util.Date;

public record CartItemResponseDto(
        Long id,
        Long productId,
        String slug,
        String productName,
        Integer discout,
        Double price,
        String thumbnailUrl,
        int quantity,
        boolean isActive,
        Date createAt,
        Date updateAt
) {

}
