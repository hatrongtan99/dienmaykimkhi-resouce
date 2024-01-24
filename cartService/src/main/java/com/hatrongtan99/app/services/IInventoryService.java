package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.stock.ProductInStockResponseDto;

public interface IInventoryService {
    ProductInStockResponseDto getQuantityInStock(Long productId);
}
