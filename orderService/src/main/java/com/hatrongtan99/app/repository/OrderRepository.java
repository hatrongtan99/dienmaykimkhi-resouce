package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
