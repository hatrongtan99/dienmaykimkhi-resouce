package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class PromotionEntity extends BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(unique = true)
    private String couponCode;

    private Integer quantity;

    private Integer percentCoupon;

    private Double minPriceAccept;

    private Double maxAmount;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private boolean isActive;
}
