package com.hatrongtan99.app.dto.PromotionProductDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public record PromotionProductSaveDto(
        @NotNull
        Long productId,

        @Min(1)
        @Max(100)
        Integer percentDiscount,

        @NotBlank
        ZonedDateTime startDate,
        @NotBlank
        ZonedDateTime endDate
) {
}
