package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.Exception.BadRequestException;
import com.hatrongtan99.app.dto.cartItemDto.CartItemSaveOrUpdateDto;
import com.hatrongtan99.app.entity.CartEntity;
import com.hatrongtan99.app.entity.CartItemEntity;
import com.hatrongtan99.app.repository.CartItemRepository;
import com.hatrongtan99.app.repository.CartRepository;
import com.hatrongtan99.app.services.ICartService;
import com.hatrongtan99.enumeration.cart.CartStatus;
import com.nimbusds.jose.crypto.impl.PRFParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService extends AbstractCartServiceHelper implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        super(cartRepository, cartItemRepository);
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public List<CartItemEntity> getCartItemUser(Long userId) {
        CartEntity cartCurrent = this.getOrCreateCartIdle(userId);
        return this.cartItemRepository.findAllCartItemByUserIdAndCartId(userId, cartCurrent.getId());
    }

    @Override
    @Transactional
    public CartEntity addOrUpdateListCartItem(Long userId, List<CartItemSaveOrUpdateDto> listCartItem) {
        CartEntity currentCart = this.getOrCreateCartIdle(userId);
        for (CartItemSaveOrUpdateDto cartItem : listCartItem) {
            this.addCartItem(userId, cartItem);
        }
        return currentCart;
    }

    @Override
    @Transactional
    public CartEntity updateCartItem(Long userId, CartItemSaveOrUpdateDto cartUpdate) {
        CartEntity mainCart = this.getOrCreateCartIdle(userId);
        // validate if null throw
        validateCartItemIsInCart(mainCart, cartUpdate.productId());
        CartItemEntity existCartItem = this.findExistItemInCart(mainCart, cartUpdate.productId());
        if (cartUpdate.quantity() == 0) {
            mainCart.getListCartItem().remove(existCartItem);
        } else {
            existCartItem.setQuantity(cartUpdate.quantity());
        }
        return this.cartRepository.saveAndFlush(mainCart);
    }

    @Override
    @Transactional
    public void deleteCartItemByListProduct(Long userId, List<Long> listProductId) {
        CartEntity mainCart = this.getOrCreateCartIdle(userId);
        for (Long productId : listProductId) {
            validateCartItemIsInCart(mainCart, productId);
            CartItemEntity cartItem = this.findExistItemInCart(mainCart, productId);
            mainCart.getListCartItem().remove(cartItem);
        }
    }

    @Override
    @Transactional
    public CartEntity addCartItem(Long userId, CartItemSaveOrUpdateDto cartItem) {
        CartEntity mainCart = this.getOrCreateCartIdle(userId);
        //  check if null create new
        if (!this.checkItemExistOnCart(mainCart, cartItem.productId())) {
            CartItemEntity newItem = CartItemEntity.builder()
                    .cartId(mainCart)
                    .productId(cartItem.productId())
                    .quantity(cartItem.quantity())
                    .build();
            mainCart.getListCartItem().add(newItem);
            this.cartItemRepository.save(newItem);
        } else {
            // else plus quantity in cartItem
            CartItemEntity existInCart = this.findExistItemInCart(mainCart, cartItem.productId());
            existInCart.setQuantity(existInCart.getQuantity() + cartItem.quantity());
        }
        return this.cartRepository.save(mainCart);
    }

    @Override
    public CartEntity changeStatusCartUser(Long userId, CartStatus status) {
        CartEntity mainCart = this.getOrCreateCartIdle(userId);
        mainCart.setStatus(status);
        return this.cartRepository.saveAndFlush(mainCart);
    }

    @Override
    @Transactional
    public Integer countCartItem(Long userId) {
        CartEntity currentCart = this.getOrCreateCartIdle(userId);
        return this.cartItemRepository.countByCartId(currentCart);
    }


}
