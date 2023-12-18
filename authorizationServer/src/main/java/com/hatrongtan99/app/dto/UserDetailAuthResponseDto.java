package com.hatrongtan99.app.dto;

import com.hatrongtan99.app.entity.RoleEntity;
import com.hatrongtan99.app.entity.UserEntity;

import java.util.List;

public record UserDetailAuthResponseDto(Long id,
                                        String fullName,
                                        String username,
                                        String email,
                                        List<String> roles,
                                        String accessToken
                               ) {
    static UserDetailAuthResponseDto mapToDto(UserEntity user, String accessToken) {
        return new UserDetailAuthResponseDto(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(RoleEntity::getName).toList(),
                accessToken
        );
    }
}
