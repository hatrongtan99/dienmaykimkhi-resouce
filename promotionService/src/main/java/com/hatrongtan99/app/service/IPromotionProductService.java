package com.hatrongtan99.app.service;

import com.hatrongtan99.app.dto.PromotionProductDto.PromotionProductSaveDto;
import com.hatrongtan99.app.entity.PromotionProductEntity;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public interface IPromotionProductService {
    PromotionProductEntity getPromotionProduct(Long productId, ZonedDateTime date);
    PromotionProductEntity createPromotionProduct(PromotionProductSaveDto body);

    void deletePromotionProduct(Long productId);
}
