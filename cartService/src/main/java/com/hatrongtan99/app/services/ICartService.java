package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.cartItemDto.CartItemSaveOrUpdateDto;
import com.hatrongtan99.app.entity.CartEntity;
import com.hatrongtan99.app.entity.CartItemEntity;
import com.hatrongtan99.enumeration.cart.CartStatus;

import java.util.List;

public interface ICartService {
    List<CartItemEntity> getCartItemUser(Long userId);
    CartEntity addOrUpdateListCartItem(Long userId, List<CartItemSaveOrUpdateDto> listCartItem);
    CartEntity updateCartItem(Long userId, CartItemSaveOrUpdateDto cartUpdate);
    void deleteCartItemByListProduct(Long userId, List<Long> listProductId);
    CartEntity addCartItem(Long userId, CartItemSaveOrUpdateDto cartItem);
    CartEntity changeStatusCartUser(Long userId, CartStatus status);
    Integer countCartItem(Long userId);
}
