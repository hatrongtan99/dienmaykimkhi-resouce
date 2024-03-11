package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.priceDto.DetailPriceProductDto;

public interface IProductService {
    DetailPriceProductDto getDetailProductPrice(Long productId);
}
