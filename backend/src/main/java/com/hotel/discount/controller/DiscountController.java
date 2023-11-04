package com.hotel.discount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.discount.interfaces.IDiscountMgt;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final IDiscountMgt discountService;

    @Autowired
    public DiscountController(IDiscountMgt discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/applySimple")
    public ResponseEntity<Void> applySimpleDiscount(@RequestParam("paramName") String paramName) {
        return ResponseEntity.ok(discountService.applySimpleDiscount());
    }
    
    @GetMapping("/applyLoyalty")
    public ResponseEntity<Void> applyLoyaltyDiscount(@RequestParam("paramName") String paramName) {
        return ResponseEntity.ok(discountService.applyLoyaltyDiscount());
    }
}
