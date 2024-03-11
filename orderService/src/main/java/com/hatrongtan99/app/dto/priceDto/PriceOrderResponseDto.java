package com.hatrongtan99.app.dto.priceDto;

public record PriceOrderResponseDto(
        double totalCostOfGoods,
        double totalCostProductDiscount,
        double deliveryFee,
        double totalCostVoucherDiscount,
        double totalSave,
        double totalPrice
) {
}
