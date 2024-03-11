package com.hatrongtan99.app.dto.promotion;

import java.time.ZonedDateTime;

public record PromotionProductResponseDto(
        Long id,
        Long productId,
        Integer percentDiscount,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
