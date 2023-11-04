package com.hotel.payments.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import com.stripe.exception.ApiException;
import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.StripeException;
import com.stripe.exception.CardException;

import com.hotel.payments.entity.ChargeRequestEntity;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;

@Service
public class StripeService {
    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequestEntity chargeRequest) 
        throws AuthenticationException, InvalidRequestException,
        ApiConnectionException, CardException, ApiException, StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}
