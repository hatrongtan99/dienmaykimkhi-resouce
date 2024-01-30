package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long userId;

    private String avatarUrl;
    private String email;
    private String fullName;
    private String numberPhone;

    @Builder.Default
    private boolean isActive = true;
}
