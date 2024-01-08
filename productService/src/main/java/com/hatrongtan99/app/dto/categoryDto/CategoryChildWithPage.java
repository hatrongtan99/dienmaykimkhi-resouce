package com.hatrongtan99.app.dto.categoryDto;

import com.hatrongtan99.app.dto.paginationDto.MetadataDto;

import java.util.List;

public record CategoryChildWithPage(
        List<CategoryResponseDto> records,
        MetadataDto _metadata
) {
}
