package com.hatrongtan99.app.service;

import com.hatrongtan99.app.dto.UserLoginRequestDto;
import com.hatrongtan99.app.dto.UserLoginResponseDto;
import com.hatrongtan99.app.dto.UserRegisterRequestDto;
import com.hatrongtan99.app.dto.UserRegisterResponseDto;

public interface IUserService {
    UserLoginResponseDto userLogin(UserLoginRequestDto userLoginRequestDto);

    UserRegisterResponseDto userRegister(UserRegisterRequestDto userRegisterRequestDto);
}
