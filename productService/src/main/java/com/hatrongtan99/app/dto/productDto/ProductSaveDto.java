package com.hatrongtan99.app.dto.productDto;

import com.hatrongtan99.app.dto.mediaDto.MediaSaveDto;
import com.hatrongtan99.app.dto.productMetadataDto.ProductMetadataSaveOrUpdateDto;
import jakarta.validation.constraints.NotBlank;
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
        @Positive
        double price,
        MediaSaveDto thumbnail,
        @NotNull
        Long brandId,
        @NotNull
        List<Long> categoryId,
        List<Long> images,
        List<Long> productRelate,
        List<ProductMetadataSaveOrUpdateDto> metadata,
        boolean isAvailInStock
) {
}
