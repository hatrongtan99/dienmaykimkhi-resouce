package com.hatrongtan99.saga.dto.inventoryProduct;

import com.hatrongtan99.enumeration.order.OrderStatus;

import java.util.List;

public record StockUpdateWhenOrderDto(
        List<StockAdjustQuantityDto> products,
        OrderStatus statusOrder
) {
}
