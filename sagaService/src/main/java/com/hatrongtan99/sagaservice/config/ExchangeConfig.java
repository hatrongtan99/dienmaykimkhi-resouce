package com.hatrongtan99.sagaservice.config;

import com.hatrongtan99.saga.constants.ExchangeName;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {
    @Bean
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(ExchangeName.DIRECT_EXCHANGE).durable(true).build();
    }
}
