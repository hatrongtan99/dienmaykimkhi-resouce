package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.StockAdjustQuantityDto;
import com.hatrongtan99.app.dto.StockUpdateWhenOrderDto;
import com.hatrongtan99.app.entity.StockEntity;
import com.hatrongtan99.app.entity.StockHistoryEntity;
import com.hatrongtan99.app.exceptions.BadRequestException;
import com.hatrongtan99.app.messageBroker.dto.ProductChangeStatusInStockDto;
import com.hatrongtan99.app.messageBroker.publisher.InventoryMessageService;
import com.hatrongtan99.app.repository.StockHistoryRepository;
import com.hatrongtan99.app.repository.StockRepository;
import com.hatrongtan99.enumeration.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    private final StockRepository stockRepository;
    private final StockHistoryRepository stockHistoryRepository;
    private final InventoryMessageService inventoryMessageService;

    @Override
    @Transactional
    public boolean productIsInStock(Long id) {
        StockEntity stock = this.getStockByProductId(id);
        return stock.getQuantity() > 0;
    }

    @Override
    @Transactional
    public void adjustQuantity(Long productId, StockAdjustQuantityDto adjustment) {
        StockEntity stock = this.getStockByProductId(productId);
        StockHistoryEntity stockHistory = StockHistoryEntity.builder()
                .stockId(stock)
                .adjustedQuantity(adjustment.adjustedQuantity())
                .note(adjustment.note())
                .build();
        stock.setQuantity(stock.getQuantity() + stockHistory.getAdjustedQuantity());
        if (stock.getQuantity() < 0) {
            throw new BadRequestException("Can't update quantity because quantity is negative");
        }
        // pub to product service change available in stock
        boolean isInStock = stock.getQuantity() > 0;
        inventoryMessageService.pubChangeStatusInStock(new ProductChangeStatusInStockDto(productId, isInStock));
        stock.getStockHistoryList().add(stockHistory);
    }
    
    @Override
    @Transactional
    public StockEntity getByProductId(Long productId) {
        return this.getStockByProductId(productId);
    }

    @Override
    public Page<StockHistoryEntity> getHistoryByProductId(Long productId, Integer pageNumber, Integer pageLimit) {
        Sort sortBy = Sort.by(Sort.Direction.DESC, "createAt");
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sortBy);
        return this.stockHistoryRepository.findByProductId(productId, pageable);
    }

    private StockEntity getStockByProductId(Long id) {
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
