package com.hatrongtan99.paymentservice.dto;

public record OrderAddressResponseDto(
    Long id,
    String fullname,
    String email,
    String phoneNumber,
    String addressLine1,
    String addressLine2,
    String addressLine3
) {
}
