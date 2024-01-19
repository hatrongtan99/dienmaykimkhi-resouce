package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.CartEntity;
import com.hatrongtan99.app.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    Integer countByCartId(CartEntity cartId);

    @Query(value = """
                FROM CartItemEntity AS i LEFT JOIN i.cartId AS c WHERE c.userId = :userId AND c.id = :cartId ORDER BY i.lastModifyAt
            """)
    List<CartItemEntity> findAllCartItemByUserIdAndCartId(@Param("userId") Long userId, @Param("cartId") Long cartId);
    Optional<CartItemEntity> findByCartIdAndProductId(CartEntity currentCart, Long ProductId);
}
