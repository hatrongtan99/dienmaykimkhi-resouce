package com.hatrongtan99.app.dto.order;

import com.hatrongtan99.app.entity.OrderEntity;
import com.hatrongtan99.app.entity.OrderItemEntity;
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
    public static DetailOrderResponseDto mapToDto(OrderEntity entity) {
        List<OrderItemEntity> listItem = entity.getOrderItemList();
        List<OrderItemResponseDto> items = listItem.stream().map(OrderItemResponseDto::mapToDto).toList();
        OrderAddressResponseDto addressDto = OrderAddressResponseDto.mapToDto(entity.getAddressId());
        return new DetailOrderResponseDto(
                entity.getId(),
                addressDto,
                entity.getNote(),
                items,
                entity.getDiscount(),
                entity.getNumberItem(),
                entity.getTotalPrice(),
                entity.getDeliveryFee(),
                entity.getOrderStatus(),
                entity.getDeliveryMethod(),
                entity.getPaymentMethod(),
                entity.getPaymentStatus(),
                entity.getRejectReason()
        );
    }
}
