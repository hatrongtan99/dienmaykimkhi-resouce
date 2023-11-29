package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.OrderAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAddressRepository extends JpaRepository<OrderAddressEntity, Long> {
}
