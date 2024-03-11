package com.hatrongtan99.paymentservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.hatrongtan99.paymentservice.repository"})
@ComponentScan(basePackages = {"com.hatrongtan99.paymentservice"})
public class AppConfig {
}
