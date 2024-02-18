package com.hatrongtan99.app.dto.PromotionDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record PromotionSaveDto(
        @NotBlank
        String name,
        String description,

        @NotNull
        Integer quantity,

        @NotNull
        @Min(1)
        @Max(100)
        Integer percentCoupon,

        @NotNull
        Double minPriceAccept,

        Double maxAmount,

        @NotNull
        ZonedDateTime startDate,

        @NotNull
        ZonedDateTime endDate
) {
}
