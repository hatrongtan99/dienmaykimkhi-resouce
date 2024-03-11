package com.hatrongtan99.app.dto;

import com.hatrongtan99.enumeration.order.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StockUpdateWhenOrderDto(
        @NotNull
        List<StockAdjustQuantityDto> products,
        OrderStatus statusOrder
) {
}
