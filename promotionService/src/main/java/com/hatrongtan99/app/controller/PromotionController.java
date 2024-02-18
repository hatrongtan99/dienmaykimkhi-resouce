package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.PromotionDto.PromotionResponseDto;
import com.hatrongtan99.app.dto.PromotionDto.PromotionSaveDto;
import com.hatrongtan99.app.entity.PromotionEntity;
import com.hatrongtan99.app.service.IPromotionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/bff-customer/promotion-code", "/bff-admin/promotion-code"})
@RequiredArgsConstructor
public class PromotionController {

    private final IPromotionService promotionService;
    private final ThreadPoolTaskScheduler taskScheduler;

    @PostMapping
    public ResponseEntity<PromotionResponseDto> createPromotionCode(
            @Valid @RequestBody PromotionSaveDto body
    ) {
        PromotionEntity promotion = this.promotionService.createPromotionCode(body);
        // active promotion code
        this.taskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                promotionService.updateActivePromotionCode(promotion.getId(), true);
            }
        }, body.startDate().toInstant());

        // disable promotion code
        this.taskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                promotionService.updateActivePromotionCode(promotion.getId(), false);
            }
        }, body.endDate().toInstant());
        return ResponseEntity.ok(PromotionResponseDto.mapToDto(promotion));
    }

    @GetMapping("/{couponCode}")
    public ResponseEntity<PromotionResponseDto> getPromotionCode(
            @PathVariable("couponCode") String couponCode
    ) {
        PromotionEntity promotion = this.promotionService.getDetailPromotionCodeByCouponCode(couponCode);
        return ResponseEntity.ok(PromotionResponseDto.mapToDto(promotion));
    }
}
