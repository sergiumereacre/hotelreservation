package com.hotel.payments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.service.PaymentService;

@RequestMapping("/invoice")
@RestController
public class InvoiceController {

    @Autowired PaymentService service;
    
    // Get invoice history
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<IInvoice>> getInvoiceHistory(int userId) {
        return ResponseEntity.ok(service.getInvoiceHistory(userId));
    }
}
