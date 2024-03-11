package com.hatrongtan99.app.dto.addressDto;

import com.hatrongtan99.app.entity.AddressUserEntity;

public record AddressUserResponseDto(
        Long id,
        Long userId,
        String fullName,
        String phoneNumber,
        String addressLine1,
        String addressLine2,
        String addressLine3,
        Boolean isDefault
) {
    public static AddressUserResponseDto mapToDto(AddressUserEntity entity) {
        return new AddressUserResponseDto(
                entity.getId(),
                entity.getUserId(),
                entity.getFullName(),
                entity.getPhoneNumber(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getAddressLine3(),
                entity.isDefault()
        );
    }
}
