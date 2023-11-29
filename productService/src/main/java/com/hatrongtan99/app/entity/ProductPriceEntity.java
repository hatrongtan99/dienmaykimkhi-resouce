package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_price")
public class ProductPriceEntity extends BaseAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ProductEntity product;

    private double  price;

    @Column(columnDefinition = "default null text")
    private String note;

    private boolean isActive;
}
