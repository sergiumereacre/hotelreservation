package com.hotel.payments.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotel.payments.service.InvoiceService;


@RequestMapping("/invoice")
@RestController
public class InvoiceController {
    

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/pending/{userId}")
    public ResponseEntity<?> getPendingInvoices(@PathVariable Long userId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByIsPaid(userId, false));
    }

    // Get invoice history
    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getInvoiceHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByIsPaid(userId, true));
    }

    @PostMapping("/pay")
    public ResponseEntity<Boolean> makePayment(@RequestBody JsonNode payload) {

        Long invoiceId = payload.get("invoiceId").asLong();
        String paymentType = payload.get("paymentType").asText();

        return ResponseEntity.ok(invoiceService.processPayment(invoiceId, paymentType));
    }

    @PostMapping("/generate-invoice")
    public ResponseEntity<?> generateInvoice(@RequestBody JsonNode payload) {

        JsonNode roomListJson = payload.get("paymentRefs");

        List<String> paymentRefList = new ArrayList<>();
        if (roomListJson.isArray()) {
            for (JsonNode roomIdJson : roomListJson) {
                paymentRefList.add(roomIdJson.asText());
            }
        }

        Long userId = payload.get("userId").asLong();

        return ResponseEntity.ok(invoiceService.generateInvoice(userId, paymentRefList));
    }

    @GetMapping("/{invoiceId}/formatted/{format}")
    public ResponseEntity<?> getFormattedInvoice(@PathVariable Long invoiceId, @PathVariable String format) {
        // return ResponseEntity.ok(invoiceService.getFormattedInvoice(invoiceId, format));

        return null;
    }
}
