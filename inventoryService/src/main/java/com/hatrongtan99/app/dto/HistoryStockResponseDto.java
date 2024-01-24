package com.hatrongtan99.app.dto;

import java.util.Date;

public record HistoryStockResponseDto(
    Long id,
    Integer adjustQuantity,
    String note,
    Date createAt
) {
}
