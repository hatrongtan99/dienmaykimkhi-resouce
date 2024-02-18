package com.hatrongtan99.app.service;

import com.hatrongtan99.app.dto.PromotionDto.PromotionSaveDto;
import com.hatrongtan99.app.entity.PromotionEntity;

public interface IPromotionService {
    PromotionEntity createPromotionCode(PromotionSaveDto body);
    PromotionEntity updateActivePromotionCode(Long promotionId, Boolean isActive);

    PromotionEntity getDetailPromotionCodeByCouponCode(String couponCode);
}
