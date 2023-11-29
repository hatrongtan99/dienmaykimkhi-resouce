package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_address")
public class OrderAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    private String email;

    @OneToOne(mappedBy = "orderId", fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    private String phoneNumber;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;
}
