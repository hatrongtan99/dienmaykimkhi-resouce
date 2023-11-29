package com.hatrongtan99.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.hatrongtan99.app"})
@EnableJpaRepositories(basePackages = {"com.hatrongtan99.app.repository"})
public class AppConfig {
}
