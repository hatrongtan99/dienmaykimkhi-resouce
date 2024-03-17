package com.hatrongtan99.paymentservice.controller;

import com.hatrongtan99.paymentservice.dto.CheckoutUrlResponse;
import com.hatrongtan99.paymentservice.service.IPaymentOnlineService;
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.invoker.AbstractReactorHttpExchangeAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bff-customer/payment-online")
@RequiredArgsConstructor
public class PaymentOnlineController {
    private final IPaymentOnlineService paymentOnlineService;
    private final String WEBHOOK_SECRET = "whsec_e24b928e51c5948ea5c80a957fb16546f21c686a8e3188408fceb93cc1def850";
    @PostMapping("/{orderId}")
    public ResponseEntity<CheckoutUrlResponse> charge(
        @PathVariable Long orderId
    ) throws StripeException {

        SessionCreateParams params = this.paymentOnlineService.getSessionCreateParams(orderId);
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
