package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_price")
public class PriceEntity extends BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private ProductEntity productId;

    private double price;

    @Column(columnDefinition = "text default null")
    private String note;

    @Column(columnDefinition = "boolean default true")
    private boolean isActive;
}
