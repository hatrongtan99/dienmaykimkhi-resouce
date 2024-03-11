package com.hatrongtan99.app.dto.order;

import com.hatrongtan99.enumeration.order.OrderStatus;

import java.util.List;

public record OrderByUserResponseDto(
        Long id,
        Double totalPrice,
        OrderStatus orderStatus,
        List<OrderItemResponseDto> items
) {
}
