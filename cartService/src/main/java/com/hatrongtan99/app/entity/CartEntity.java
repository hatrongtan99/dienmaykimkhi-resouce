package com.hatrongtan99.app.entity;

import com.hatrongtan99.enumeration.cart.CartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    private Long orderId;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<CartItemEntity> cartItemList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CartStatus status;
}
