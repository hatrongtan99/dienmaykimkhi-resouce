package com.hatrongtan99.sagaservice.ochestration.service;

import com.hatrongtan99.saga.constants.ExchangeName;
import com.hatrongtan99.saga.constants.QueueName;
import com.hatrongtan99.saga.constants.RoutingKey;
import com.hatrongtan99.saga.dto.inventoryProduct.StockUpdateWhenOrderDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderInventoryService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = QueueName.ORDER_TO_SAGA_UPDATE_QUANTITY_PRODUCT_IN_STOCK_QUEUE_NAME)
    public void updateQuantityInStock(StockUpdateWhenOrderDto message) {
        this.rabbitTemplate.convertAndSend(ExchangeName.DIRECT_EXCHANGE, RoutingKey.SAGA_TO_INVENTORY_UPDATE_QUANTITY_IN_STOCK_ROUTE_KEY, message);
    }
}
