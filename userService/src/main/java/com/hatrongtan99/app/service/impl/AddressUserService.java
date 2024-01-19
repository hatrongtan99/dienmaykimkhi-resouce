package com.hatrongtan99.app.service.impl;

import com.hatrongtan99.app.dto.addressDto.AddressUserSaveOrUpdateDto;
import com.hatrongtan99.app.entity.AddressUserEntity;
import com.hatrongtan99.app.exception.BadRequest;
import com.hatrongtan99.app.exception.NotFoundException;
import com.hatrongtan99.app.repository.AddressUserEntityRepository;
import com.hatrongtan99.app.service.IUserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAddressService implements IUserAddressService {
    private final AddressUserEntityRepository addressUserEntityRepository;
    public AddressUserEntity getAddressMain(Long userId) {
        return null;
    }

    @Override
    public List<AddressUserEntity> getAllAddress(Long userId) {
        return this.addressUserEntityRepository.findAllByUserIdOrderByMainDesc(userId);
    }

    @Override
    public AddressUserEntity createNewAddress(Long userId, AddressUserSaveOrUpdateDto addressUser) {
        AddressUserEntity newAddress = AddressUserEntity.builder()
                .userId(userId)
                .phoneNumber(addressUser.phoneNumber())
                .addressLine1(addressUser.addressLine1())
                .addressLine2(addressUser.addressLine2())
                .addressLine3(addressUser.addressLine3())
                .build();
        // if size == 0, new address is default
        int count = this.addressUserEntityRepository.countAddressUserEntityByUserId(userId);
        if (count == 0) {
            newAddress.setMain(true);
        }
        return this.addressUserEntityRepository.save(newAddress);
    }

    @Override
    public AddressUserEntity updateAddress(Long userId, AddressUserSaveOrUpdateDto addressUser) {
       this.validateAddress(userId, addressUser.id());
       AddressUserEntity exist = this.addressUserEntityRepository.findById(addressUser.id()).get();
       exist.setPhoneNumber(addressUser.phoneNumber());
       exist.setAddressLine1(addressUser.addressLine1());
       exist.setAddressLine2(addressUser.addressLine2());
       exist.setAddressLine3(addressUser.addressLine3());
       return this.addressUserEntityRepository.save(exist);
    }

    @Override
    public void deleteAddressUser(Long userId, Long addressId) {
        validateAddress(userId, addressId);
        AddressUserEntity exist = this.addressUserEntityRepository.findByIdAndUserId(addressId, userId).get();
        if (exist.isMain()) {
            throw new BadRequest("can't delete main address");
        }
        this.addressUserEntityRepository.delete(exist);
    }

    @Override
    public void updateIsMainAddress(Long userId, Long addressId) {
        this.validateAddress(userId, addressId);
        AddressUserEntity exist = this.addressUserEntityRepository.findByIdAndUserId(addressId, userId).get();
        if (exist.isMain()) return;
        AddressUserEntity mainAddress = this.addressUserEntityRepository.findByUserIdAndMainIsTrue(userId);
        if (mainAddress == null) {
            throw new NotFoundException("not found");
        }
        mainAddress.setMain(false);
        exist.setMain(true);
        this.addressUserEntityRepository.saveAllAndFlush(List.of(exist, mainAddress));
    }

    private void validateAddress(Long userId, Long addressId) {
        Optional<AddressUserEntity> exist = this.addressUserEntityRepository.findByIdAndUserId(addressId, userId);
        if (exist.isEmpty()){
            throw new NotFoundException("not found");
        }
    }
}
