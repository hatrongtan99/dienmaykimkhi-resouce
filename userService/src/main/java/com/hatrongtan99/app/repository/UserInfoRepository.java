package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByUserIdAndIsActiveIsTrue(Long userId);
}
