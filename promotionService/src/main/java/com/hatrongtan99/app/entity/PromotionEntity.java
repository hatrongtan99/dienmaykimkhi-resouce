package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;

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

    private String minPriceAccept;

    private Double totalAmount;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private boolean isActive;
}
