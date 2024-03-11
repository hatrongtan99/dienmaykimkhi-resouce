package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.AddressUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressUserEntityRepository extends JpaRepository<AddressUserEntity, Long> {
    List<AddressUserEntity> findByUserIdOrderByIsDefaultDesc(Long userId);
    Optional<AddressUserEntity> findByIdAndUserId(Long id, Long userId);
    Integer countAddressUserEntityByUserId(Long userId);
    Optional<AddressUserEntity> findByUserIdAndIsDefaultIsTrue(Long useId);
}
