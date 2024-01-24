package com.hatrongtan99.app.dto;

import com.hatrongtan99.app.dto.paginationDto.MetadataDto;

import java.util.List;

public record HistoryStockWithPageableResponseDto(
        List<HistoryStockResponseDto> records,
        MetadataDto _metadata
) {
}
