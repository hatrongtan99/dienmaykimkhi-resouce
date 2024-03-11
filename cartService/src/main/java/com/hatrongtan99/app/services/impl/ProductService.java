package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.config.PropertiesConfig;
import com.hatrongtan99.app.dto.priceDto.DetailPriceProductDto;
import com.hatrongtan99.app.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final PropertiesConfig propertiesConfig;
    private final WebClient webClient;
    @Override
    public DetailPriceProductDto getDetailProductPrice(Long productId) {
        final URI detailPriceProductUri = UriComponentsBuilder
                .fromUriString(this.propertiesConfig.getProductServiceUrl())
                .path("/bff-customer/products/cart-item/{productId}").buildAndExpand(productId).toUri();
        return this.webClient
                .get().uri(detailPriceProductUri)
                .retrieve().bodyToMono(DetailPriceProductDto.class).block();
    }
}
