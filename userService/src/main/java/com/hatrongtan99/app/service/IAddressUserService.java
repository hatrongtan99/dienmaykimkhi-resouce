package com.hatrongtan99.app.service;

import com.hatrongtan99.app.dto.addressDto.AddressUserSaveOrUpdateDto;
import com.hatrongtan99.app.entity.AddressUserEntity;

import java.util.List;

public interface IUserAddressService {
    AddressUserEntity getAddressMain(Long userId);
    List<AddressUserEntity> getAllAddress(Long userId);
    AddressUserEntity createNewAddress(Long userId, AddressUserSaveOrUpdateDto addressUser);
    AddressUserEntity updateAddress(Long userId, AddressUserSaveOrUpdateDto addressUser);
    void deleteAddressUser(Long userId, Long addressId);
    void updateIsMainAddress(Long userId, Long addressId);
}
