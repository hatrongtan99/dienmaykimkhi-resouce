package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.config.PropertiesConfig;
import com.hatrongtan99.app.dto.priceDto.DetailPriceProductDto;
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
    public ProductInStockResponseDto getQuantityInStock(Long productId) {
        final URI uriDetailQuantity = UriComponentsBuilder.fromUriString(this.propertiesConfig.getInventoriesServiceUrl())
                .path("/bff-customer/inventory/{productId}").buildAndExpand(productId)
                .toUri();

        return this.webClient.get()
                .uri(uriDetailQuantity)
                .headers(h -> h.setBearerAuth(AuthenticationUtils.getToken()))
                .retrieve()
                .bodyToMono(ProductInStockResponseDto.class)
                .block();
    }
}
