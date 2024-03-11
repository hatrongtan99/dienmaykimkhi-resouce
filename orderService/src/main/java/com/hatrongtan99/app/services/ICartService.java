package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.priceDto.TotalPriceCartResponseDto;
import com.hatrongtan99.enumeration.cart.CartStatus;

public interface ICartService {
    void changeCartStatus(CartStatus cartStatus);

    TotalPriceCartResponseDto getDetailPriceCartUser(Long userId);
}
