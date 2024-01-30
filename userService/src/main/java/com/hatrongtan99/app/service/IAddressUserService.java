package com.hatrongtan99.app.service;

import com.hatrongtan99.app.dto.addressDto.AddressUserSaveOrUpdateDto;
import com.hatrongtan99.app.entity.AddressUserEntity;

import java.util.List;

public interface IAddressUserService {
    AddressUserEntity getAddressById(Long userId, Long addressId);
    List<AddressUserEntity> getAllAddress(Long userId);
    AddressUserEntity createNewAddress(Long userId, AddressUserSaveOrUpdateDto addressUser);
    AddressUserEntity updateAddress(Long userId, Long addressId, AddressUserSaveOrUpdateDto addressUser);
    void deleteAddressUser(Long userId, Long addressId);
    void updateDefaultAddress(Long userId, Long addressId);
}
