package com.hatrongtan99.app.messageBroker.publisher;

import com.hatrongtan99.app.dto.inventory.StockUpdateWhenOrderDto;
import com.hatrongtan99.saga.constants.ExchangeName;
import com.hatrongtan99.saga.constants.RoutingKey;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryMessageService {
    private final RabbitTemplate rabbitTemplate;

    public void pubChangeStatusInStock(StockUpdateWhenOrderDto message) {
        this.rabbitTemplate.convertAndSend(ExchangeName.DIRECT_EXCHANGE, RoutingKey.ORDER_TO_SAGA_UPDATE_QUANTITY_IN_STOCK_ROUTE_KEY, message);
    }
}
