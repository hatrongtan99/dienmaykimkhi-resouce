package com.hatrongtan99.app.dto.categoryDto;

import com.hatrongtan99.app.dto.paginationDto.MetadataDto;

import java.util.List;

public record CategoryWithPageDto(
        MetadataDto _metadata,
        List<CategoryResponseDto> records
) {
}
