package com.hatrongtan99.paymentservice.service;

import com.stripe.param.checkout.SessionCreateParams;

public interface IPaymentOnlineService {
    SessionCreateParams getSessionCreateParams(Long orderId);
}
