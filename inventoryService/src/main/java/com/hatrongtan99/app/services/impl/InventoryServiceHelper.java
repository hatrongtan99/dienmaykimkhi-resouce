package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.entity.StockEntity;
import com.hatrongtan99.app.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class InventoryServiceHelper {
    @Autowired
    private StockRepository stockRepository;

    protected StockEntity getStockByProductId(Long id) {
        StockEntity stock = this.stockRepository.findByProductId(id)
                .orElse(StockEntity.builder()
                        .productId(id)
                        .quantity(0)
                        .quantitySold(0)
                        .build());

        this.stockRepository.save(stock);
        return stock;
    }
}
