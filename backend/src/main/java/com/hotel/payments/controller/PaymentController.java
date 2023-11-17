package com.hotel.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotel.payments.entity.CardEntity;
import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.service.InvoiceService;
import com.hotel.payments.service.PaymentService;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<PaymentEntity>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/payment/{paymentRef}")
    public ResponseEntity<PaymentEntity> getPaymentByRef(@PathVariable String paymentRef) {
        return ResponseEntity.ok(paymentService.getPaymentByRef(paymentRef));
    }

    @PutMapping("/processCardPayment/{invoiceID}")
    public ResponseEntity<String> processCardPayment(@RequestBody CardEntity cardEntity, @PathVariable long invoiceID) {

        try {
            invoiceService.processPaymentWithCard(cardEntity, invoiceID);
            // Additional logic if needed
            return ResponseEntity.ok("Card payment processed successfully.");
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            // Handle other exceptions
            // Print e
            return ResponseEntity.badRequest().body(e.getMessage());
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/processCashPayment/{invoiceID}")
    public ResponseEntity<String> processCashPayment(@PathVariable long invoiceID) {
        // long invoiceID = payload.get("invoiceID").asLong();        
        
        try {
            invoiceService.processPaymentWithCash(invoiceID);
            // Additional logic if needed
            return ResponseEntity.ok("Cash payment processed successfully.");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/validateCashPayment/{invoiceID}")
    public ResponseEntity<String> validateCashPayment(@RequestBody JsonNode payload, @PathVariable long invoiceID) {
        // long invoiceID = payload.get("invoiceID").asLong();
        long guestID = payload.get("guestID").asLong();

        try {
            invoiceService.validateCashPayment(invoiceID, guestID);
            // Additional logic if needed
            return ResponseEntity.ok("Cash payment confirmed and validated successfully.");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
