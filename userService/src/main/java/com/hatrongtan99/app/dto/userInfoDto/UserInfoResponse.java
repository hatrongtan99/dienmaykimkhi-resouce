package com.hatrongtan99.app.dto.userInfoDto;

import com.hatrongtan99.app.entity.UserInfoEntity;

public record UserInfoResponse(
        Long id,
        Long userId,
        String avatarUrl,
        String fullName,
        String email,
        String numberPhone,
        boolean isActive
) {
    public static UserInfoResponse mapToDto(UserInfoEntity userInfo) {
        return new UserInfoResponse(
                userInfo.getId(),
                userInfo.getUserId(),
                userInfo.getAvatarUrl(),
                userInfo.getFullName(),
                userInfo.getNumberPhone(),
                userInfo.getEmail(),
                userInfo.isActive()
        );
    }
}
