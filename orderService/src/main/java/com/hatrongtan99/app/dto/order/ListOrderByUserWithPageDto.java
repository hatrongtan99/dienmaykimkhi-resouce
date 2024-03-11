package com.hatrongtan99.app.dto.order;

import com.hatrongtan99.app.dto.paginationDto.MetadataDto;

import java.util.List;

public record ListOrderByUserWithPageDto(
        List<OrderByUserResponseDto> records,
        MetadataDto _metadata
) {
}
