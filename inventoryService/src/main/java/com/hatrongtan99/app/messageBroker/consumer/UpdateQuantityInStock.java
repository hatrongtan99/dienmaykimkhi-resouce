package com.hatrongtan99.app.messageBroker.consumer;

import com.hatrongtan99.app.services.IInventoryService;
import com.hatrongtan99.saga.constants.QueueName;
import com.hatrongtan99.saga.dto.inventoryProduct.StockUpdateWhenOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateQuantityInStock {
    private final IInventoryService inventoryService;

    @RabbitListener(queues = QueueName.SAGA_TO_INVENTORY_UPDATE_QUANTITY_PRODUCT_IN_STOCK_QUEUE_NAME)
    @Transactional
    public void updateQuantityInStock(StockUpdateWhenOrderDto message) {
        this.inventoryService.updateWhenOrder(message);
    }
}
