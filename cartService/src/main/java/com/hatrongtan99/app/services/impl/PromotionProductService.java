package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.config.PropertiesConfig;
import com.hatrongtan99.app.dto.promotion.PromotionProductResponseDto;
import com.hatrongtan99.app.services.IPromotionProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PromotionProductService implements IPromotionProduct {

    private final WebClient webClient;
    private final PropertiesConfig propertiesConfig;
    @Override
    public PromotionProductResponseDto getPromotionProduct(Long id) {
        String dateISO = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
        URI uri = UriComponentsBuilder.fromHttpUrl(this.propertiesConfig.getPromotionServiceUrl()).path("/bff-customer/products/{id}/{date}").buildAndExpand(id, dateISO).toUri();
        return this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(PromotionProductResponseDto.class)
                .block();
    }
}
