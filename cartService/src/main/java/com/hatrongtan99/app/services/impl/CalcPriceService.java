package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.Exception.NotFoundException;
import com.hatrongtan99.app.config.PropertiesConfig;
import com.hatrongtan99.app.dto.priceDto.DetailPriceProductDto;
import com.hatrongtan99.app.dto.priceDto.TotalPriceDto;
import com.hatrongtan99.app.entity.CartEntity;
import com.hatrongtan99.app.entity.CartItemEntity;
import com.hatrongtan99.app.repository.CartItemRepository;
import com.hatrongtan99.app.repository.CartRepository;
import com.hatrongtan99.app.services.ICalcPriceService;
import com.hatrongtan99.enumeration.cart.CartStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class CalcPriceService extends AbstractCartServiceHelper implements ICalcPriceService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final WebClient webClient;
    private final PropertiesConfig propertiesConfig;

    public CalcPriceService(CartRepository cartRepository, CartItemRepository cartItemRepository, WebClient webClient, PropertiesConfig propertiesConfig) {
        super(cartRepository, cartItemRepository);
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.webClient = webClient;
        this.propertiesConfig = propertiesConfig;
    }

    @Override
    @Transactional
    public TotalPriceDto calcTotalPrice(Long userId) {
        CartEntity mainCart = this.cartRepository.findByUserIdAndStatus(userId, CartStatus.IDLE)
                .orElseThrow(() -> new NotFoundException("not found"));
        List<CartItemEntity> listCartItem = this.cartItemRepository.findAllCartItemByUserIdAndCartId(userId, mainCart.getId());
        double totalCostOfGoods = 0;
        double totalCostProductDiscount = 30000;
        for (CartItemEntity cartItem : listCartItem) {
            final URI detailPriceProductUri = UriComponentsBuilder
                    .fromUriString(this.propertiesConfig.getProductServiceUrl())
                    .path("/bff-customer/products/cart-item/{productId}").buildAndExpand(cartItem.getProductId()).toUri();
            DetailPriceProductDto detailPrice = this.webClient
                    .get().uri(detailPriceProductUri)
                    .retrieve().bodyToMono(DetailPriceProductDto.class).block();
            assert detailPrice != null;
            totalCostOfGoods += detailPrice.price() * cartItem.getQuantity();
        }
        double totalPrice = totalCostOfGoods == 0 ? 0 : totalCostOfGoods - totalCostProductDiscount;
        return new TotalPriceDto(
                totalCostOfGoods,
                totalCostProductDiscount,
                totalCostProductDiscount,
                totalPrice
        );
    }
}
