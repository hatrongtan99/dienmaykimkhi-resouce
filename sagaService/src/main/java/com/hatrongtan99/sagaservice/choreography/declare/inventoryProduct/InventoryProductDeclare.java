package com.hatrongtan99.sagaservice.choreography.declare.inventoryProduct;

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
public class InventoryProductDeclare {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private DirectExchange directExchange;
    @PostConstruct
    private void init() {
        Queue q = new Queue(QueueName.CHANGE_STATUS_IN_STOCK_QUEUE_NAME, true);
        rabbitAdmin.declareQueue(q);
        rabbitAdmin.declareBinding(BindingBuilder.bind(q).to(directExchange).with(RoutingKey.CHANGE_STATUS_IN_STOCK_ROUTE_KEY));
    }

}
