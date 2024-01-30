package com.hatrongtan99.app.service.impl;

import com.hatrongtan99.app.dto.userInfoDto.UserInfoSaveOrUpdateDto;
import com.hatrongtan99.app.entity.UserInfoEntity;
import com.hatrongtan99.app.repository.UserInfoRepository;
import com.hatrongtan99.app.service.IUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService implements IUserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserInfoEntity getUserInfoById(Long userId) {
        Optional<UserInfoEntity> userInfo = this.userInfoRepository.findByUserIdAndIsActiveIsTrue(userId);
        return userInfo.orElse(null);
    }

    @Override
    public UserInfoEntity createOrUpdate(Long userId, UserInfoSaveOrUpdateDto body) {
        Optional<UserInfoEntity> userInfo = this.userInfoRepository.findByUserIdAndIsActiveIsTrue(userId);
        UserInfoEntity userInfoSave = null;
        if (userInfo.isPresent()) {
            userInfoSave = userInfo.get();
            userInfoSave.setAvatarUrl(body.avatarUrl());
            userInfoSave.setFullName(body.fullName());
            userInfoSave.setEmail(body.email());
            userInfoSave.setNumberPhone(body.numberPhone());
        } else {
            userInfoSave = UserInfoEntity.builder()
                    .userId(userId)
                    .avatarUrl(body.avatarUrl())
                    .fullName(body.fullName())
                    .email(body.email())
                    .numberPhone(body.numberPhone())
                    .build();
        }
        return this.userInfoRepository.saveAndFlush(userInfoSave);
    }
}
