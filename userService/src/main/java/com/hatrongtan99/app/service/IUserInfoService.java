package com.hatrongtan99.app.service;

import com.hatrongtan99.app.dto.userInfoDto.UserInfoSaveOrUpdateDto;
import com.hatrongtan99.app.entity.UserInfoEntity;

public interface IUserInfoService {
    UserInfoEntity getUserInfoById(Long userId);
    UserInfoEntity createOrUpdate(Long userId, UserInfoSaveOrUpdateDto body);
}
