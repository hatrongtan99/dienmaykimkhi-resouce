package com.hatrongtan99.app.messageBroker.publisher;

import com.hatrongtan99.saga.constants.RoutingKey;
import com.hatrongtan99.saga.dto.inventoryProduct.ProductChangeStatusInStockDto;
import com.hatrongtan99.saga.constants.ExchangeName;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryMessageService {
    private final RabbitTemplate rabbitTemplate;

    public void pubChangeStatusInStock(ProductChangeStatusInStockDto message) {
        this.rabbitTemplate.convertAndSend(ExchangeName.DIRECT_EXCHANGE, RoutingKey.CHANGE_STATUS_IN_STOCK_ROUTE_KEY, message);
    }
}
