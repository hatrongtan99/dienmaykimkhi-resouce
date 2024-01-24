package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.StockAdjustQuantityDto;
import com.hatrongtan99.app.dto.StockUpdateWhenOrderDto;
import com.hatrongtan99.app.entity.StockEntity;
import com.hatrongtan99.app.entity.StockHistoryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IInventoryService {
    boolean productIsInStock(Long id);

    void adjustQuantity(Long productId, StockAdjustQuantityDto adjustment);

    void updateWhenOrder(Long productId, StockUpdateWhenOrderDto updateDto);

    StockEntity getByProductId(Long productId);

    Page<StockHistoryEntity> getHistoryByProductId(Long productId, Integer pageNumber, Integer pageLimit);
}
