package com.hatrongtan99.paymentservice.service.impl;

import com.hatrongtan99.paymentservice.dto.DetailOrderResponseDto;
import com.hatrongtan99.paymentservice.service.IOrderService;
import com.hatrongtan99.paymentservice.service.IPaymentOnlineService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentOnlineService implements IPaymentOnlineService {
    private final IOrderService orderService;

    @Value("${STRIPE_WEBHOOK_SECRET_KEY}")
    private String webhookSecretKey;

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = this.secretKey;
    }


    @Override
    public SessionCreateParams getSessionCreateParams(Long orderId) {
        DetailOrderResponseDto orderInfo = this.orderService.getDetailOrder(orderId);

        List<SessionCreateParams.LineItem> lineItems =orderInfo.items().stream()
                .map(productItem -> SessionCreateParams.LineItem.builder()
                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency("usd")
                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(productItem.productName())
                                .addImage(productItem.thumbnailUrl())
                                .build())
                        .setUnitAmount(Long.valueOf(productItem.productPrice().toString()))
                        .build())
                .setQuantity(Long.valueOf(productItem.quantity().toString()))
                .build()).toList();


        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setSuccessUrl("http://localhost:8080/payment-success")
                        .setCancelUrl("http://localhost:8080/cart")
                        .setCurrency("usd")
                        .addAllLineItem(
                                lineItems
                        )
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .putMetadata("orderId", orderId.toString())
                        .build();
        return params;
    }
}
