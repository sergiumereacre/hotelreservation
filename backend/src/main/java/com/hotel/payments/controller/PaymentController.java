package com.hotel.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.payments.entity.CardEntity;
import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.service.CardPaymentCommand;
import com.hotel.payments.service.CashPaymentCommand;
import com.hotel.payments.service.PaymentInvoker;
import com.hotel.payments.service.PaymentService;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired PaymentService service;

    @GetMapping("/all")
    public ResponseEntity<Iterable<PaymentEntity>> getAllPayments() {
        return ResponseEntity.ok(service.getAllPayments());
    }

    @GetMapping("/payment/{paymentRef}")
    public ResponseEntity<PaymentEntity> getPaymentByRef(@PathVariable String paymentRef) {
        return ResponseEntity.ok(service.getPaymentByRef(paymentRef));
    }

    @PostMapping("/processCardPayment")
    public ResponseEntity<String> processCardPayment(@RequestBody CardEntity cardEntity) {
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(new CardPaymentCommand(cardEntity, service));
        
        try {
            paymentInvoker.processPayment();
            // Additional logic if needed
            return ResponseEntity.ok("Card payment processed successfully.");
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/processCashPayment")
    public ResponseEntity<String> processCashPayment() {
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(new CashPaymentCommand(service));

        try {
            paymentInvoker.processPayment();
            // Additional logic if needed
            return ResponseEntity.ok("Cash payment processed successfully.");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
