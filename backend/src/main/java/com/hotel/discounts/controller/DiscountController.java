package com.hotel.discounts.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.discounts.entity.DiscountRequestEntity;
import com.hotel.discounts.service.DiscountService;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    
    private final DiscountService discountMgt;

    DiscountController(DiscountService discountMgt) {
        this.discountMgt = discountMgt;
    }

    @PostMapping("/applySimpleDiscount")
    public void applySimpleDiscount(@RequestBody DiscountRequestEntity request) {
        discountMgt.applySimpleDiscount(request.getChargeable(), request.getFlatDiscount(), request.getPercentageDiscount(), request.getApplierId());
    }

    @PostMapping("/applyLoyaltyDiscount")
    public void applyLoyaltyDiscount(@RequestBody DiscountRequestEntity request) {
        discountMgt.applyLoyaltyDiscount(request.getChargeable(), request.getLoyaltyStatus());
    }
}
