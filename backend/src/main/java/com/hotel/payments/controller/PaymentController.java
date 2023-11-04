package com.hotel.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.hotel.payments.entity.ChargeRequestEntity;
import com.hotel.payments.entity.ChargeRequestEntity.Currency;
import com.hotel.payments.service.PaymentService;
import com.hotel.payments.service.StripeService;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Autowired PaymentService service;
    @Autowired StripeService stripeService;

    @PostMapping("/pay")
    public ResponseEntity<Boolean> makePayment(int invoiceId, String paymentType) {
        return ResponseEntity.ok(service.processPayment(invoiceId, paymentType));
    }

    @PostMapping("/charge")
    public String charge(ChargeRequestEntity chargeRequest, Model model)
        throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(Currency.EUR);
        Charge charge = stripeService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

}
