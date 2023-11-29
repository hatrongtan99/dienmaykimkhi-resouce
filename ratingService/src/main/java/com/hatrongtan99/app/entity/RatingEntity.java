package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rating")
public class RatingEntity extends BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ratingStar;

    private String content;
    private Long productId;
    private Long userId;
    private Long orderId;
    private boolean isActive;
}
