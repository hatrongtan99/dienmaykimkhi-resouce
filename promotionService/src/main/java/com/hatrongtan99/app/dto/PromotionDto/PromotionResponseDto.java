package com.hatrongtan99.app.dto.PromotionDto;

import com.hatrongtan99.app.entity.PromotionEntity;

import java.time.ZonedDateTime;

public record PromotionResponseDto(
        Long id,
        String name,
        String description,
        String couponCode,
        Integer quantity,
        Integer percentCoupon,
        Double minPriceAccept,
        Double maxAmount,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        Boolean isActive
) {

    public static PromotionResponseDto mapToDto(PromotionEntity entity) {
        return new PromotionResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCouponCode(),
                entity.getQuantity(),
                entity.getPercentCoupon(),
                entity.getMinPriceAccept(),
                entity.getMaxAmount(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.isActive()
        );
    }
}
