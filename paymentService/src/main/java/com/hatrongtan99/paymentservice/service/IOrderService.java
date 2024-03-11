package com.hatrongtan99.paymentservice.service;

import com.hatrongtan99.paymentservice.dto.DetailOrderResponseDto;

public interface IOrderService {

    DetailOrderResponseDto getDetailOrder(Long orderId);
}
