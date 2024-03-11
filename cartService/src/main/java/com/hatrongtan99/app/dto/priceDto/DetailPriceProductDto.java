package com.hatrongtan99.app.dto.priceDto;

public record DetailPriceProductDto(
        Long id,
        String name,
        String slug,
        double price,
        String thumbnail
) {
}
