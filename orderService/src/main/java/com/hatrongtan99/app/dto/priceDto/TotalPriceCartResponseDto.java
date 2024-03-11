package com.hatrongtan99.app.dto.priceDto;

public record TotalPriceCartResponseDto(
        double totalCostOfGoods,
        double totalCostProductDiscount,
        double totalSave,
        double totalPrice
) {
}
