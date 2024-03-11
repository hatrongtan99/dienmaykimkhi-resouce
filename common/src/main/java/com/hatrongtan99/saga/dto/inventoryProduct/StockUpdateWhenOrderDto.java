package com.hatrongtan99.sagaservice.ochestration.dto.inventoryDto;

import com.hatrongtan99.enumeration.order.OrderStatus;

import java.util.List;

public record StockUpdateWhenOrderDto(
        List<StockAdjustQuantityDto> products,
        OrderStatus statusOrder
) {
}
