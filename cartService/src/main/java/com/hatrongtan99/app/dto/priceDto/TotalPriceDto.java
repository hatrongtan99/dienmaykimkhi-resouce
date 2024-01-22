package com.hatrongtan99.app.dto.priceDto;

public record TotalPriceDto(
        double totalCostOfGoods,
        double totalCostProductDiscount,
        double totalSave,
        double totalPrice
) {
}
