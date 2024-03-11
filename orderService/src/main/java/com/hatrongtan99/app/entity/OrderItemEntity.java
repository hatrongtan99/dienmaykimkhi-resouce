package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    private String productName;
    private String thumbnailUrl;

    private double productPrice;

    private int quantity;

    private Integer discount;
}
