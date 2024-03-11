package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.OrderEntity;
import com.hatrongtan99.enumeration.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByIdAndUserId(Long id, Long userId);

    @Query(value = """
            FROM OrderEntity AS o WHERE o.userId = :userId AND (:orderStatus IS NULL OR o.orderStatus = :orderStatus) ORDER BY o.createAt DESC
            """)
    Page<OrderEntity> findByUserIdAndStatusFilter(@Param("userId") Long userId, @Param("orderStatus") OrderStatus orderStatus, Pageable pageable);
}
