package com.hatrongtan99.app.dto.order;

import jakarta.validation.constraints.NotNull;

public record CancelOrderRequestDto(
        @NotNull
        Long orderId,
        String reason
) {
}
