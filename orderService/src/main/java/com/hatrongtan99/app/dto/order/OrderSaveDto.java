package com.hatrongtan99.app.dto.order;

import com.hatrongtan99.enumeration.order.PaymentMethod;

import java.util.List;

public record OrderSaveDto(
        String fullName,
        String email,
        String phoneNumber,
        String addressLine1,
        String addressLine2,
        String addressLine3,
        List<OrderItemRequestDto> cartItems,
        String couponCode,
        double discount,
        double totalPrice,
        double deliveryFee,
        String deliveryMethod,
        PaymentMethod paymentMethod,
        String note
) {
}
