package com.hatrongtan99.app.entity;

import com.hatrongtan99.enumeration.cart.CartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class CartEntity extends BaseAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "cartId")
    private Set<CartItemEntity> listCartItem = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CartStatus status = CartStatus.IDLE;
}
