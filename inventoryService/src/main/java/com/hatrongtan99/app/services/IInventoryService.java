package com.hatrongtan99.app.services;

import com.hatrongtan99.app.entity.StockEntity;
import com.hatrongtan99.app.entity.StockHistoryEntity;
import com.hatrongtan99.saga.dto.inventoryProduct.StockAdjustQuantityDto;
import com.hatrongtan99.saga.dto.inventoryProduct.StockUpdateWhenOrderDto;
import org.springframework.data.domain.Page;

public interface IInventoryService {
    boolean productIsInStock(Long id);

    void adjustQuantity(Long productId, StockAdjustQuantityDto adjustment);

    void updateWhenOrder(StockUpdateWhenOrderDto updateDto);

    StockEntity getByProductId(Long productId);

    Page<StockHistoryEntity> getHistoryByProductId(Long productId, Integer pageNumber, Integer pageLimit);
}
