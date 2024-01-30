package com.hatrongtan99.app.controller;


import com.hatrongtan99.app.dto.userInfoDto.UserInfoResponse;
import com.hatrongtan99.app.dto.userInfoDto.UserInfoSaveOrUpdateDto;
import com.hatrongtan99.app.entity.UserInfoEntity;
import com.hatrongtan99.app.service.IUserInfoService;
import com.hatrongtan99.app.utils.AuthenticationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping({"/bff-customer/users", "/bff-admin/users"})
@RequiredArgsConstructor
public class UserInfoController {

    private final IUserInfoService userInfoService;
    @PostMapping
    public ResponseEntity<UserInfoResponse> createNewOrGetDetailInfo(
            @Valid @RequestBody UserInfoSaveOrUpdateDto body
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        UserInfoEntity userInfo = this.userInfoService.getUserInfoById(userId);
        if (userInfo == null) {
            userInfo = this.userInfoService.createOrUpdate(userId, body);
        }
        return ResponseEntity.ok(UserInfoResponse.mapToDto(userInfo));
    }

    @PutMapping
    public ResponseEntity<Void> updateUserInfo(
            @Valid @RequestBody UserInfoSaveOrUpdateDto body
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.userInfoService.createOrUpdate(userId, body);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserInfoResponse> getDetailInfo(
            @PathVariable("userId") Long userId
    ) {
        UserInfoEntity userInfo = this.userInfoService.getUserInfoById(userId);
        return ResponseEntity.ok(UserInfoResponse.mapToDto(userInfo));
    }

}
