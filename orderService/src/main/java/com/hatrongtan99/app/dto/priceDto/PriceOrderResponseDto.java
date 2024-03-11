package com.hatrongtan99.app.dto;

public record PriceOrderResponseDto(
        double totalCostOfGoods,
        double totalCostProductDiscount,
        double totalSave,
        double totalPrice
) {
}
