package com.hotel.payments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.hotel.payments.entity.ChargeRequestEntity;
import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.entity.ChargeRequestEntity.Currency;
import com.hotel.payments.service.PaymentService;
import com.hotel.payments.service.StripeService;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired PaymentService service;
    @Autowired StripeService stripeService;
    

    @GetMapping("/all")
    public ResponseEntity<Iterable<PaymentEntity>> getAllPayments() {
        return ResponseEntity.ok(service.getAllPayments());
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

     @GetMapping("/payment/{paymentRef}")
    public ResponseEntity<PaymentEntity> getPaymentByRef(@PathVariable String paymentRef) {
        return ResponseEntity.ok(service.getPaymentByRef(paymentRef));
    }

}
