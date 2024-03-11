package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.priceDto.PriceOrderResponseDto;
import com.hatrongtan99.app.dto.order.CancelOrderRequestDto;
import com.hatrongtan99.app.dto.order.OrderSaveDto;
import com.hatrongtan99.app.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    OrderEntity createOrder(Long userId, OrderSaveDto body);
    OrderEntity getDetailOrder(Long userId, Long id);
    Page<OrderEntity> getOrdersByUserId(Long userId, Pageable pageable, String orderStatus);
    void cancelOrder(Long userId, CancelOrderRequestDto requestDto);
    PriceOrderResponseDto calcPriceOrder(Long userId, String vouchers);

}
