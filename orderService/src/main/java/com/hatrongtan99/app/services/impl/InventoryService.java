package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.config.PropertiesConfig;
import com.hatrongtan99.app.dto.inventory.StockUpdateWhenOrderDto;
import com.hatrongtan99.app.dto.stock.ProductInStockResponseDto;
import com.hatrongtan99.app.services.IInventoryService;
import com.hatrongtan99.app.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    private final PropertiesConfig propertiesConfig;
    private final WebClient webClient;
    @Override
    public ProductInStockResponseDto getQuantityInStock(Long id) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.propertiesConfig.getInventoryServiceUrl())
                .path("/bff-customer/inventory/{productId}").buildAndExpand(id).toUri();
        return this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ProductInStockResponseDto.class)
                .block();
    }


}
