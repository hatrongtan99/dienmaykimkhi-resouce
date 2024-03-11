package com.hatrongtan99.paymentservice.dto;

import com.hatrongtan99.enumeration.order.OrderStatus;
import com.hatrongtan99.enumeration.order.PaymentMethod;
import com.hatrongtan99.enumeration.order.PaymentStatus;

import java.util.List;

public record DetailOrderResponseDto(
        Long id,
        OrderAddressResponseDto address,
        String note,
        List<OrderItemResponseDto> items,
        double discount,
        int numberItem,
        double totalPrice,
        double deliveryFree,
        OrderStatus orderStatus,
        String deliveryMethod,
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus,
        String rejectReason
) {
}
