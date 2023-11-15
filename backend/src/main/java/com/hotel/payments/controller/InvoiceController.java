package com.hotel.payments.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.service.InvoiceService;
import com.hotel.payments.service.PaymentService;

@RequestMapping("/invoice")
@RestController
public class InvoiceController {

    // @Autowired
    // PaymentService paymentService;

    @Autowired
    InvoiceService invoiceService;

    // Get invoice history
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<IInvoice>> getInvoiceHistory(int userId) {
        // return ResponseEntity.ok(invoiceService.getInvoiceHistory(userId));
        return null;
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
}
