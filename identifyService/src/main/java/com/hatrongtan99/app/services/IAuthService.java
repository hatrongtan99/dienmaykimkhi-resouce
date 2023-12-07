package com.hatrongtan99.app.services;

import com.hatrongtan99.app.config.securitory.CustomUserDetail;
import com.hatrongtan99.app.dto.UserDetailAuthResponseDto;
import com.hatrongtan99.app.dto.UserLoginRequestDto;
import com.hatrongtan99.app.dto.UserRegisterRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {
    String userLogin(UserLoginRequestDto userLogin, HttpServletResponse response) throws Exception;
    UserDetailAuthResponseDto authenticated(CustomUserDetail principal) throws Exception;

    String register(UserRegisterRequestDto userInfo);


}
