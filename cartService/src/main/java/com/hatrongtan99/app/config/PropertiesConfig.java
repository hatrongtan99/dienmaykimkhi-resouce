package com.hatrongtan99.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "properties")
@Getter
@Setter
public class PropertiesConfig {
    private String productServiceUrl;
    private String mediaServiceUrl;
    private String inventoriesServiceUrl;
}
