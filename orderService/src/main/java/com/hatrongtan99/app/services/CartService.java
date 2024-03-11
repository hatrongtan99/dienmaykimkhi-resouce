package com.hatrongtan99.app.services;

import com.hatrongtan99.app.config.PropertiesConfig;
import com.hatrongtan99.app.dto.cartDto.UpdateCartStatusDto;
import com.hatrongtan99.app.dto.priceDto.TotalPriceCartResponseDto;
import com.hatrongtan99.app.utils.AuthenticationUtils;
import com.hatrongtan99.enumeration.cart.CartStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;


@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final PropertiesConfig propertiesConfig;
    private final WebClient webClient;

    @Override
    public void changeCartStatus(CartStatus cartStatus) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.propertiesConfig.getCartServiceUrl())
                .path("/bff-customer/cart/status").buildAndExpand().toUri();

        this.webClient.patch().uri(uri)
                .headers(h -> h.setBearerAuth(AuthenticationUtils.getToken()))
                .body(Mono.just(new UpdateCartStatusDto(cartStatus)), UpdateCartStatusDto.class)
                .retrieve().bodyToMono(Void.class).block();
    }

    @Override
    public TotalPriceCartResponseDto getDetailPriceCartUser(Long userId) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.propertiesConfig.getCartServiceUrl())
                .path("/bff-customer/cart-price")
                .build()
                .toUri();
        return this.webClient.get()
                .uri(uri)
                .headers(h -> h.setBearerAuth(AuthenticationUtils.getToken()))
                .retrieve()
                .bodyToMono(TotalPriceCartResponseDto.class)
                .block();
    }
}
