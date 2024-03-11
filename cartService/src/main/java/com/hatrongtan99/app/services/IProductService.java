package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.priceDto.DetailPriceProductDto;

public interface ProductService {
    DetailPriceProductDto getDetailProductPrice(Long productId);
}
