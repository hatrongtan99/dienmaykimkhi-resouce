package com.hatrongtan99.app.dto.productAttributeDto;

import com.hatrongtan99.app.dto.ProductAttributeItemDto.ProductAttributeItemSaveDto;

import java.util.List;

public record ProductAttributeListItemSaveDto(
        Long attributeId,
        List<ProductAttributeItemSaveDto> items
) {
}
