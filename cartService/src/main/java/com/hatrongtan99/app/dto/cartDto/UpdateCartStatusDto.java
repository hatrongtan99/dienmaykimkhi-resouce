package com.hatrongtan99.app.dto.cartDto;

import com.hatrongtan99.enumeration.cart.CartStatus;

public record UpdateCartStatusDto(
        CartStatus cartStatus
) {
}
