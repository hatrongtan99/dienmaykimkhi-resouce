package com.hatrongtan99.sagaservice.ochestration.declare;

import com.hatrongtan99.saga.constants.QueueName;
import com.hatrongtan99.saga.constants.RoutingKey;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventDeclare {

    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @PostConstruct
    public void init() {
        Queue q = new Queue(QueueName.SAGA_TO_INVENTORY_UPDATE_QUANTITY_PRODUCT_IN_STOCK_QUEUE_NAME, true);
        this.rabbitAdmin.declareQueue(q);
        this.rabbitAdmin.declareBinding(BindingBuilder.bind(q).to(this.directExchange).with(RoutingKey.SAGA_TO_INVENTORY_UPDATE_QUANTITY_IN_STOCK_ROUTE_KEY));
    }
}
