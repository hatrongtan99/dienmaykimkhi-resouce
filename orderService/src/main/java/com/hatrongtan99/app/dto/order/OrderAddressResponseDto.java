package com.hatrongtan99.app.dto.order;

import com.hatrongtan99.app.entity.OrderAddressEntity;

public record OrderAddressResponseDto(
    Long id,
    String fullname,
    String email,
    String phoneNumber,
    String addressLine1,
    String addressLine2,
    String addressLine3
) {

    public static OrderAddressResponseDto mapToDto(OrderAddressEntity entity) {
        return new OrderAddressResponseDto(
                entity.getId(),
                entity.getFullname(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getAddressLine3()
        );
    }
}
