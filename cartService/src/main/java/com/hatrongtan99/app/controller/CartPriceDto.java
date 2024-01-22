package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.priceDto.DetailPriceProductDto;
import com.hatrongtan99.app.dto.priceDto.TotalPriceDto;
import com.hatrongtan99.app.services.ICalcPriceService;
import com.hatrongtan99.app.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/bff-admin/cart-price", "/bff-customer/cart-price"})
@RequiredArgsConstructor
public class CartPriceDto {

    private final ICalcPriceService calcPriceService;

    @GetMapping
    public ResponseEntity<TotalPriceDto> getInfoPriceCurrentCart() {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        TotalPriceDto result = this.calcPriceService.calcTotalPrice(userId);
        return ResponseEntity.ok(result);
    }
}
