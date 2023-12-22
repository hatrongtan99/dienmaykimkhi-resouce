package com.hatrongtan99.app.dto.filterGroupDto;

import com.hatrongtan99.app.dto.productFilterDto.ProductFilterSaveOrUpdate;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record FilterGroupSaveOrUpdateDto(
        @NotBlank
        String name,
        List<ProductFilterSaveOrUpdate> productFilters,
        @NotBlank
        boolean isActive
) {
}
