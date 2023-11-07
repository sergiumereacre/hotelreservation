package com.hotel.discount.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.discount.entity.DiscountRequest;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    
    private final DiscountMgt discountMgt;

    DiscountController(DiscountMgt discountMgt) {
        this.discountMgt = discountMgt;
    }

    @PostMapping("/applySimpleDiscount")
    public void applySimpleDiscount(@RequestBody DiscountRequest request) {
        discountMgt.applySimpleDiscount(request.getChargeable(), request.getFlatDiscount(), request.getPercentageDiscount(), request.getApplierId());
    }

    @PostMapping("/applyLoyaltyDiscount")
    public void applyLoyaltyDiscount(@RequestBody DiscountRequest request) {
        discountMgt.applyLoyaltyDiscount(request.getChargeable(), request.getLoyaltyStatus());
    }
}
