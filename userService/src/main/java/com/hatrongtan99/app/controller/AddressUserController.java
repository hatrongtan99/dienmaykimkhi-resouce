package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.addressDto.AddressUserResponseDto;
import com.hatrongtan99.app.dto.addressDto.AddressUserSaveOrUpdateDto;
import com.hatrongtan99.app.entity.AddressUserEntity;
import com.hatrongtan99.app.service.IAddressUserService;
import com.hatrongtan99.app.utils.AuthenticationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff-customer/users/address")
@RequiredArgsConstructor
public class AddressUserController {
    private final IAddressUserService addressUserService;

    @GetMapping
    public ResponseEntity<List<AddressUserResponseDto>> getAllAddress() {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        List<AddressUserResponseDto> result = this.addressUserService.getAllAddress(userId).stream().map(AddressUserResponseDto::mapToDto).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressUserResponseDto> getDetailAddress(
            @PathVariable("addressId") Long addressId
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        AddressUserEntity addressUser = this.addressUserService.getAddressById(userId, addressId);
        return ResponseEntity.ok(AddressUserResponseDto.mapToDto(addressUser));
    }

    @PostMapping
    public ResponseEntity<Void> createNewAddress(
            @Valid @RequestBody AddressUserSaveOrUpdateDto body
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.addressUserService.createNewAddress(userId, body);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Void> updateAddress(
            @PathVariable("addressId") Long addressId,
            @Valid @RequestBody AddressUserSaveOrUpdateDto body
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.addressUserService.updateAddress(userId, addressId, body);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<Void> changeDefault(
            @PathVariable("addressId") Long addressId
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.addressUserService.updateDefaultAddress(userId, addressId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddressUser(
            @PathVariable("addressId") Long addressId
    ) {
        Long userId = AuthenticationUtils.getAuthenticationUserId();
        this.addressUserService.deleteAddressUser(userId, addressId);
        return ResponseEntity.noContent().build();
    }
}
