package com.hatrongtan99.app.dto.productDto;


public record ProductLineCartResponseDto(
        Long id,
        String name,
        String slug,
        double price,
        String thumbnail

) {
}
