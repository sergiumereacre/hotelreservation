package com.hotel.discount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<void> applySimpleDiscount(@RequestParam ) {
        return ResponseEntity.ok(discountService.applySimpleDiscount());
    }
    
    @GetMapping("/applyLoyalty")
    public ResponseEntity<void> applyLoyaltyDiscount(@RequestParam ) {
        return ResponseEntity.ok(discountService.applyLoyaltyDiscount());
    }
}
