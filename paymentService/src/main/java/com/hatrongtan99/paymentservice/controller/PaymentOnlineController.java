package com.hatrongtan99.paymentservice.controller;

import com.hatrongtan99.paymentservice.dto.CheckoutUrlResponse;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.Invoice;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.invoker.AbstractReactorHttpExchangeAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bff-customer/payment-online")
public class PaymentOnlineController {

    private final String WEBHOOK_SECRET = "whsec_e24b928e51c5948ea5c80a957fb16546f21c686a8e3188408fceb93cc1def850";
    @PostMapping("/{orderId}")
    public ResponseEntity<CheckoutUrlResponse> charge(
        @PathVariable String orderId
    ) throws StripeException {

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency("usd")
                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName("product name")
                                .addImage("https://img.super-mro.com/super-mro/2022/04/w550/1y358-may-khoan-van-vit-dung-pin-dewalt-dcd708n-kr.jpg.webp")
                                .build())
                        .setUnitAmount(200L)
                        .build())
                .setQuantity(1L)
                .build();

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setSuccessUrl("http://localhost:8080/payment-success")
                        .setCancelUrl("http://localhost:8080/cart")
                        .setCurrency("usd")
                        .addAllLineItem(
                                List.of(lineItem, lineItem, lineItem, lineItem)
                        )
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .putMetadata("orderId", "orderId")
                        .build();
        Session session = Session.create(params);
        return ResponseEntity.ok(new CheckoutUrlResponse(session.getUrl()));
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> validatePaymentEvent(@RequestBody String json, HttpServletRequest request) throws StripeException {
        String sigHeader = request.getHeader("Stripe-Signature");

        Event event = null;
        try {
            event = Webhook.constructEvent(json, sigHeader, WEBHOOK_SECRET);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
        }
        assert event != null;
        switch (event.getType()) {
            case "charge.succeeded":
                Charge charge = (Charge) event.getData().getObject();
                var metadata = charge.getMetadata();
                break;
            case "checkout.session.completed":
                Session session = (Session) event.getData().getObject();
                var test =  session.getMetadata();
                break;
            case "charge.failed":
                System.out.println("failed");
                break;
            default:
                System.out.println(event.getType());
        }

        return ResponseEntity.noContent().build();
    }
}
