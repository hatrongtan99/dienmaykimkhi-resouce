package com.hatrongtan99.app.service.impl;

import com.hatrongtan99.app.dto.PromotionDto.PromotionSaveDto;
import com.hatrongtan99.app.dto.PromotionProductDto.PromotionProductSaveDto;
import com.hatrongtan99.app.entity.PromotionEntity;
import com.hatrongtan99.app.exception.NotFoundException;
import com.hatrongtan99.app.repository.PromotionRepository;
import com.hatrongtan99.app.service.IPromotionService;
import com.hatrongtan99.app.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {
    private static final Integer CODE_LENGTH = 8;
    private final PromotionRepository promotionRepository;

    @Override
    @Transactional
    public PromotionEntity createPromotionCode(PromotionSaveDto body) {

        String couponCodeGenerated = getCouponCode();

        PromotionEntity savePromotion = PromotionEntity.builder()
                .name(body.name())
                .description(body.description())
                .couponCode(couponCodeGenerated)
                .quantity(body.quantity())
                .percentCoupon(body.percentCoupon())
                .minPriceAccept(body.minPriceAccept())
                .maxAmount(body.maxAmount())
                .startDate(body.startDate())
                .endDate(body.endDate())
                .isActive(false)
                .build();
        return this.promotionRepository.saveAndFlush(savePromotion);
    }

    @Override
    @Transactional
    public PromotionEntity updateActivePromotionCode(Long promotionId, Boolean isActive) {
        PromotionEntity promotionCode = this.promotionRepository.findById(promotionId)
                .orElseThrow(() -> new NotFoundException("not found"));

        promotionCode.setActive(isActive);
        return promotionCode;
    }

    @Override
    public PromotionEntity getDetailPromotionCodeByCouponCode(String couponCode) {
        return this.promotionRepository.findByCouponCodeAndIsActiveIsTrue(couponCode)
                .orElseThrow(() -> new NotFoundException("not found"));
    }

    private String getCouponCode() {
        String couponCode = null;
        while (couponCode == null) {
            String genCode = CommonUtils.generateCouponCode(CODE_LENGTH);
            boolean isExist = this.promotionRepository.existsByCouponCode(genCode);
            if (!isExist) {
                couponCode = genCode;
            }
        }
        return couponCode;
    }

}
