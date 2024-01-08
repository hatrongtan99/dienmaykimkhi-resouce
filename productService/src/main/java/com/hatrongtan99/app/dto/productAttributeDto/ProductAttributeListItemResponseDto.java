package com.hatrongtan99.app.dto.productAttributeDto;

import com.hatrongtan99.app.dto.ProductAttributeItemDto.ProductAttributeItemResponseDto;

import java.util.List;

public record ProductAttributeListItemResponseDto(
        Long id,
        String name,
        boolean isActive,
        List<ProductAttributeItemResponseDto> items
) {
}
