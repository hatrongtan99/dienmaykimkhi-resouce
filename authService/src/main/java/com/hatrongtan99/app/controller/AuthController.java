package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.UserLoginRequestDto;
import com.hatrongtan99.app.dto.UserLoginResponseDto;
import com.hatrongtan99.app.dto.UserRegisterRequestDto;
import com.hatrongtan99.app.dto.UserRegisterResponseDto;
import com.hatrongtan99.app.service.IUserService;
import com.hatrongtan99.dto.ResponseObject;
import com.hatrongtan99.app.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public ResponseEntity<ResponseObject<UserLoginResponseDto>> userLogin(@RequestBody UserLoginRequestDto userLoginDto) {
//        return ResponseEntity.ok(new ResponseObject<>(true, "Login success", this.userService.userLogin(userLoginDto)));
        throw new NotFoundException("user not found");
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject<UserRegisterResponseDto>> userRegister(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject<>(true, "Register success", this.userService.userRegister(userRegisterRequestDto)));
    }

    @GetMapping
    public ResponseEntity<String> test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getName());
        System.out.println(authentication.getPrincipal());
        return ResponseEntity.ok("success");
    }
}
