package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.promotion.PromotionProductResponseDto;

public interface IPromotionProduct {
    PromotionProductResponseDto getPromotionProduct(Long id);
}
