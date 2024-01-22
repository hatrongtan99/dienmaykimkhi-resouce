package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.Exception.BadRequestException;
import com.hatrongtan99.app.entity.CartEntity;
import com.hatrongtan99.app.entity.CartItemEntity;
import com.hatrongtan99.app.repository.CartItemRepository;
import com.hatrongtan99.app.repository.CartRepository;
import com.hatrongtan99.enumeration.cart.CartStatus;

import java.util.Optional;

public abstract class AbstractCartServiceHelper {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public AbstractCartServiceHelper(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    protected CartEntity getOrCreateCartIdle(Long userId) {
        Optional<CartEntity> cartIdle = this.cartRepository.findByUserIdAndStatus(userId, CartStatus.IDLE);
        if (cartIdle.isEmpty()) {
            CartEntity newCart = CartEntity.builder()
                    .userId(userId)
                    .build();
            return this.cartRepository.saveAndFlush(newCart);
        }
        return cartIdle.get();
    }

    protected boolean checkItemExistOnCart(CartEntity currentCart, Long productId) {
        return this.cartItemRepository.findByCartIdAndProductId(currentCart, productId).isPresent();
    }

    protected void validateCartItemIsInCart(CartEntity currentCart, Long productId) {
        if (!this.checkItemExistOnCart(currentCart, productId)) {
            throw new BadRequestException("cart item is not in cart");
        }
    }

    protected CartItemEntity findExistItemInCart(CartEntity currentCart, Long productId) {
        return this.cartItemRepository.findByCartIdAndProductId(currentCart, productId).get();
    }
}
