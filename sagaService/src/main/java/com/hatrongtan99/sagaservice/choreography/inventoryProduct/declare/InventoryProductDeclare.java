package com.hatrongtan99.sagaservice.choreography.inventoryProduct.declare;

import com.hatrongtan99.saga.constants.QueueName;
import com.hatrongtan99.saga.constants.RoutingKey;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryProductBinding {
    @Bean
    public Queue changeStatusInstockQueue() {
        return new Queue(QueueName.CHANGE_STATUS_IN_STOCK_QUEUE_NAME);
    }

    @Bean
    public org.springframework.amqp.core.Binding binding(DirectExchange directExchange, Queue changeStatusInstockQueue) {
        return BindingBuilder.bind(changeStatusInstockQueue).to(directExchange).with(RoutingKey.CHANGE_STATUS_IN_STOCK_ROUTE_KEY);
    }

}
