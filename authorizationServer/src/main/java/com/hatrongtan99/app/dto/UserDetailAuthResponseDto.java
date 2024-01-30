package com.hatrongtan99.app.dto;

import com.hatrongtan99.app.entity.RoleEntity;
import com.hatrongtan99.app.entity.UserEntity;

import java.util.List;

public record UserDetailAuthResponseDto(Long id,
                                        String username,
                                        List<String> roles,
                                        String accessToken
                               ) {

}
