package com.hatrongtan99.app.dto.PromotionProductDto;

import com.hatrongtan99.app.entity.PromotionProductEntity;

import java.time.ZonedDateTime;

public record PromotionProductResponseDto(
        Long id,
        Long productId,
        Integer percentDiscount,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
    public static PromotionProductResponseDto mapToDto(PromotionProductEntity entity) {
        return new PromotionProductResponseDto(
                entity.getId(),
                entity.getProductId(),
                entity.getPercentDiscount(),
                entity.getStartDate(),
                entity.getEndDate()
        );
    }
}
