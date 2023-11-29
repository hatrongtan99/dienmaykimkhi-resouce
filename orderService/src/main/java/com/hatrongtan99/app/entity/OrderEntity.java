package com.hatrongtan99.app.entity;

import com.hatrongtan99.enumeration.order.OrderStatus;
import com.hatrongtan99.enumeration.order.PaymentMethod;
import com.hatrongtan99.enumeration.order.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_table")
public class OrderEntity extends BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST}, optional = false)
    @JoinColumn(name = "address_id")
    private OrderAddressEntity addressId;

    @Column(columnDefinition = "text default null")
    private String note;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<OrderItemEntity> orderItemList = new ArrayList<>();

    private double discount;

    private int numberItem;

    private double totalPrice;

    private double deliveryFee;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50) default `PENDING`")
    private OrderStatus orderStatus;

    private String deliveryMethod;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50) default `PENDING`")
    private PaymentStatus paymentStatus;

    @Column(columnDefinition = "text default null")
    private String rejectReason;
}
