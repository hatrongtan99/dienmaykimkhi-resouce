package com.hatrongtan99.app.dto.productDto;

import com.hatrongtan99.app.dto.brandDto.BrandCardDto;
import com.hatrongtan99.app.entity.BrandEntity;
import com.hatrongtan99.app.entity.ProductEntity;

public record ProductCardDto(
        Long id,
        String name,
        String slug,
        String thumbnail,
        double price,
        BrandCardDto brand
) { }
