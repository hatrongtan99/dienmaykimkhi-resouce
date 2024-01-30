package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String fullName;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;

    @Builder.Default
    private boolean isDefault = false;

    @Builder.Default
    private boolean isActive = true;
}
