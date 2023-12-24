package com.hatrongtan99.app.dto.productDto;

import com.hatrongtan99.app.dto.paginationDto.MetadataDto;

import java.util.List;

public record ProductGetListWithPageDto(
        List<ProductCardDto> records,
        MetadataDto _metadata
) {
}
