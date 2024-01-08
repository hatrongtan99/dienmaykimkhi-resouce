package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.config.PropertiesConfig;
import com.hatrongtan99.app.services.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    private final WebClient webClient;
    private final PropertiesConfig propertiesConfig;

    @Override
    public boolean productIsInStock(Long id) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.propertiesConfig.getInventoryUrl())
                .path("/inventory/{id}").buildAndExpand(id).toUri();
        return Boolean.TRUE.equals(this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }
}  
