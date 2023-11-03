package com.hotel.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hotel.payments.service.PaymentService;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Autowired PaymentService service;

    @PostMapping("/pay")
    public ResponseEntity<Boolean> makePayment(int invoiceId, String paymentType) {
        return ResponseEntity.ok(service.processPayment(invoiceId, paymentType));
    }



}
