package com.hatrongtan99.paymentservice.service.impl;

import com.hatrongtan99.paymentservice.config.PropertiesConfig;
import com.hatrongtan99.paymentservice.dto.DetailOrderResponseDto;
import com.hatrongtan99.paymentservice.service.IOrderService;
import com.hatrongtan99.paymentservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final WebClient webClient;
    private final PropertiesConfig propertiesConfig;
    @Override
    public DetailOrderResponseDto getDetailOrder(Long orderId) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.propertiesConfig.getOrderUrl())
                .path("/orders/bff-customer/orders/{orderId}").buildAndExpand(orderId).toUri();

        return this.webClient.get()
                .uri(uri)
                .headers(h -> h.setBearerAuth(AuthenticationUtils.getToken()))
                .retrieve()
                .bodyToMono(DetailOrderResponseDto.class)
                .block();
    }
}
