package com.hotel.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.payments.entity.PaymentEntity;
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

}
