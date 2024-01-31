package com.hatrongtan99.app.service.impl;

import com.hatrongtan99.app.dto.PromotionProductDto.PromotionProductSaveDto;
import com.hatrongtan99.app.entity.PromotionEntity;
import com.hatrongtan99.app.entity.PromotionProductEntity;
import com.hatrongtan99.app.exception.NotFoundException;
import com.hatrongtan99.app.repository.PromotionProductRepository;
import com.hatrongtan99.app.service.IPromotionProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionProductService implements IPromotionProductService {

    private final PromotionProductRepository promotionProductRepository;

    @Override
    public PromotionProductEntity getPromotionProduct(Long productId, ZonedDateTime date) {
        return this.promotionProductRepository.findPromotionProduct(productId, date)
                .orElse(null);
    }

    @Override
    @Transactional
    public PromotionProductEntity createPromotionProduct(PromotionProductSaveDto body) {

        Optional<PromotionProductEntity> existActive = this.promotionProductRepository.findByProductIdAndIsActiveIsTrue(body.productId());
        existActive.ifPresent(promotionProductEntity -> promotionProductEntity.setActive(false));
        PromotionProductEntity promotionSave = PromotionProductEntity.builder()
                .productId(body.productId())
                .percentDiscount(body.percentDiscount())
                .startDate(body.startDate())
                .endDate(body.endDate())
                .isActive(true)
                .build();
        return this.promotionProductRepository.saveAndFlush(promotionSave);
    }

    @Override
    public void deletePromotionProduct(Long productId) {
        PromotionProductEntity exist = this.promotionProductRepository.findByProductIdAndIsActiveIsTrue(productId)
                .orElseThrow(() -> new NotFoundException("not found"));
        exist.setActive(false);
        this.promotionProductRepository.save(exist);
    }
}
