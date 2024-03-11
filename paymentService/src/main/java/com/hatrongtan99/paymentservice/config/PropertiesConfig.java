package com.hatrongtan99.paymentservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "properties")
@Getter
@Setter
public class PropertiesConfig {
    private String mediaUrl;
    private String inventoryUrl;
    private String orderUrl;
}
