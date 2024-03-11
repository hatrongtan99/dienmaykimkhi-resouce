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
public class OrderEventDeclare {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private DirectExchange directExchange;

    @PostConstruct
    public void init() {
        Queue q = new Queue(QueueName.ORDER_TO_SAGA_UPDATE_QUANTITY_PRODUCT_IN_STOCK_QUEUE_NAME, true);
        this.rabbitAdmin.declareQueue(q);
        this.rabbitAdmin.declareBinding(BindingBuilder.bind(q).to(directExchange).with(RoutingKey.ORDER_TO_SAGA_UPDATE_QUANTITY_IN_STOCK_ROUTE_KEY));
    }
}
