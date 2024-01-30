package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.cartItemDto.*;
import com.hatrongtan99.app.services.ICartService;
import com.hatrongtan99.app.utils.AuthenticationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/bff-admin/cart", "/bff-customer/cart"})
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItemResponseDto>> getCurrentCart() {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        List<CartItemResponseDto> list = this.cartService.getCartItemUser(userId).stream().map(CartItemResponseDto::mapToDto).toList();
        return ResponseEntity.ok(list);
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

}
