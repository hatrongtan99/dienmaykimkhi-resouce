package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.config.securitory.CustomUserDetail;
import com.hatrongtan99.app.dto.UserDetailAuthResponseDto;
import com.hatrongtan99.app.dto.UserLoginRequestDto;
import com.hatrongtan99.app.dto.UserRegisterRequestDto;
import com.hatrongtan99.app.services.IAuthService;
import com.hatrongtan99.dto.ResponseObject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject<String>> userLogin(@Valid @RequestBody UserLoginRequestDto userLogin, HttpServletResponse response) throws Exception {

        return ResponseEntity.ok(new ResponseObject<>(true, "success", this.userService.userLogin(userLogin, response)));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject<String>> userRegister(@Valid @RequestBody UserRegisterRequestDto userInfo) {
        return ResponseEntity.ok(new ResponseObject<>(true, "success", userService.register(userInfo)));
    }

    @GetMapping("/authenticate")
    public ResponseEntity<ResponseObject<UserDetailAuthResponseDto>> authenticate(@AuthenticationPrincipal() CustomUserDetail principal) throws Exception {
        return ResponseEntity.ok(new ResponseObject<>(true, "success", userService.authenticated(principal)));
    }

}
