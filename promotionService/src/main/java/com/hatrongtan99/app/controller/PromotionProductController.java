package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.PromotionProductDto.PromotionProductResponseDto;
import com.hatrongtan99.app.dto.PromotionProductDto.PromotionProductSaveDto;
import com.hatrongtan99.app.entity.PromotionProductEntity;
import com.hatrongtan99.app.service.IPromotionProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping({"/bff-customer/products", "/bff-admin/products"})
@RequiredArgsConstructor
public class PromotionProductController {
    private final IPromotionProductService promotionProductService;

    @GetMapping("/{productId}/{date}")
    public ResponseEntity<PromotionProductResponseDto> getPromotionProduct(
            @PathVariable("productId") Long id,
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") ZonedDateTime date
    ) {
        PromotionProductEntity promotion = this.promotionProductService.getPromotionProduct(id, date);
        if (promotion == null) {
            promotion = new PromotionProductEntity(null, id, 0, null, null, false);
        }
        return ResponseEntity.ok(PromotionProductResponseDto.mapToDto(promotion));
    }

    @PostMapping()
    public ResponseEntity<Void> createPromotion(
            @Valid @RequestBody PromotionProductSaveDto body
    ) {
        this.promotionProductService.createPromotionProduct(body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public  ResponseEntity<Void> deletePromotion(
            @PathVariable("productId") Long productId
    ) {
        this.promotionProductService.deletePromotionProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
