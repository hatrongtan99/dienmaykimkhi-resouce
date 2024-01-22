package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.priceDto.TotalPriceDto;


public interface ICalcPriceService {
    TotalPriceDto calcTotalPrice(Long userId);
}
