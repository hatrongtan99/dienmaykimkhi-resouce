package com.hatrongtan99.app.dto.cartItemDto;

import java.util.List;

public record AddMultipleItemDto(
        List<CartItemSaveOrUpdateDto> listItem
) {
}
