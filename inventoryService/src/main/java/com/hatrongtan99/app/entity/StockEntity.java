package com.hatrongtan99.app.entity;

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
@Table(name = "stock_table")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int quantity;

    private int quantitySold;

    @OneToMany(mappedBy = "stockId", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<StockHistoryEntity> StockHistoryList = new ArrayList<>();
}
