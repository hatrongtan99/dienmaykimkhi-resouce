package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.cartDto.UpdateCartStatusDto;
import com.hatrongtan99.app.dto.cartItemDto.*;
import com.hatrongtan99.app.dto.priceDto.DetailPriceProductDto;
import com.hatrongtan99.app.dto.promotion.PromotionProductResponseDto;
import com.hatrongtan99.app.entity.CartItemEntity;
import com.hatrongtan99.app.services.ICartService;
import com.hatrongtan99.app.services.IProductService;
import com.hatrongtan99.app.services.IPromotionProduct;
import com.hatrongtan99.app.utils.AuthenticationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/bff-admin/cart", "/bff-customer/cart"})
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;
    private final IProductService productService;
    private final IPromotionProduct promotionProduct;

    @GetMapping
    public ResponseEntity<List<CartItemResponseDto>> getCurrentCart() {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        List<CartItemEntity> cartItems = this.cartService.getCartItemUser(userId);
        List<CartItemResponseDto> result = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartItems) {
            DetailPriceProductDto priceLineProduct = this.productService.getDetailProductPrice(cartItemEntity.getProductId());
            PromotionProductResponseDto promotionProduct = this.promotionProduct.getPromotionProduct(cartItemEntity.getProductId());
            assert (priceLineProduct != null);
            assert (promotionProduct != null);
            result .add(new CartItemResponseDto(
                    cartItemEntity.getId(),
                    cartItemEntity.getProductId(),
                    priceLineProduct.slug(),
                    priceLineProduct.name(),
                    promotionProduct.percentDiscount(),
                    priceLineProduct.price(),
                    priceLineProduct.thumbnail(),
                    cartItemEntity.getQuantity(),
                    cartItemEntity.isActive(),
                    cartItemEntity.getCreateAt(),
                    cartItemEntity.getLastModifyAt()
            ));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/count")
    public ResponseEntity<CountCartItemResponseDto> countCurrenCartItem(@AuthenticationPrincipal DefaultOidcUser oidcUser) {

        Long userId = AuthenticationUtils.getAuthenticationUserId();
        int count = this.cartService.countCartItem(userId);
        return ResponseEntity.ok(new CountCartItemResponseDto(count));
    }

    @PatchMapping
    public ResponseEntity<Void> updateCartItem(
            @Valid @RequestBody CartItemSaveOrUpdateDto cartItem
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.cartService.updateCartItem(userId, cartItem);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> addNewItem(
            @Valid @RequestBody CartItemSaveOrUpdateDto cartItem
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.cartService.addCartItem(userId, cartItem);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/multiple")
    public ResponseEntity<Void> addMultipleCartItem(
            @Valid @RequestBody AddMultipleItemDto body
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.cartService.addOrUpdateListCartItem(userId, body.listItem());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteItemByProductId(
            @Valid @RequestBody ProductIdDeleteItemDto body
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.cartService.deleteCartItemByListProduct(userId, body.productIds());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/status")
    public ResponseEntity<Void> changeStatus(
            @RequestBody UpdateCartStatusDto cartStatusDto
    ) {
        Long idUser = AuthenticationUtils.getAuthenticationUserId();
        this.cartService.changeStatusCartUser(idUser, cartStatusDto.cartStatus());
        return ResponseEntity.noContent().build();
    }

}
