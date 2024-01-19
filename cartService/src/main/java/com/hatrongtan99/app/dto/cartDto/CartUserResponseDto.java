package com.hatrongtan99.app.dto.cartDto;

import com.hatrongtan99.app.dto.cartItemDto.CartItemResponseDto;
import com.hatrongtan99.app.entity.CartEntity;
import com.hatrongtan99.enumeration.cart.CartStatus;

import java.util.List;

public record CartUserResponseDto(
        Long id,
        Long userId,
        List<CartItemResponseDto> cartItem,
        CartStatus status
) {
//    public static CartUserResponseDto mapToDto(CartEntity cart) {
//        return new CartUserResponseDto(
//                cart.getId(),
//                cart.getUserId(),
//                cart.getListCartItem().stream().map(i -> new CartItemResponseDto(i.getId(), i.getProductId(), i.getQuantity())).toList(),
//                cart.getStatus()
//        );
//    }
}
