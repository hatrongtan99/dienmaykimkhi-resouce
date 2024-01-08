package com.hatrongtan99.app.dto.productDto;

import com.hatrongtan99.app.dto.productAttributeDto.ProductAttributeListItemSaveDto;
import com.hatrongtan99.app.dto.productMetadataDto.ProductMetadataSaveOrUpdateDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record ProductSaveDto(
        @NotBlank
        String name,
        String shortDescription,
        String description,
        @NotBlank
        String sku,
        @NotBlank
        String slug,
        String guarantee,
        @Positive
        double price,
        @NotNull
        Long thumbnailId,
        @NotNull
        Long brandId,
        @NotNull
        List<Long> categoryId,
        List<Long> images,
        List<Long> productRelate,
        List<ProductMetadataSaveOrUpdateDto> metadata,
        List<ProductAttributeListItemSaveDto> attributes,
        boolean isAvailInStock
) {
}
