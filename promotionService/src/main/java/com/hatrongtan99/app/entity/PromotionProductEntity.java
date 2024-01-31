package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class PromotionProductEntity extends BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    private Integer percentDiscount;

    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime endDate;

    private boolean isActive;
}
