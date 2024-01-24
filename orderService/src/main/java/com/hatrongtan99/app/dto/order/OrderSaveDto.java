package com.hatrongtan99.app.dto.checkout;

public record CheckoutSaveDto(
        Long addressId,
        Long userId,
        Long cartId,
        String couponCode
) {
}
