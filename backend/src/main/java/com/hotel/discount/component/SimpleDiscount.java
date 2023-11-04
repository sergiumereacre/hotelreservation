package com.hotel.discount.component;

import org.springframework.stereotype.Component;

import com.hotel.discount.entity.DiscountDecoratorEntity;

@Component
class SimpleDiscount extends DiscountDecoratorEntity {
    
    SimpleDiscount(IChargeable chargeable, double flatDiscount, double percentageDiscount) {
        super(chargeable);
        this.flatDiscount = flatDiscount;
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public double getPrice() {
        double originalPrice = chargeable.getPrice();
        double discountedPrice = originalPrice - flatDiscount;
        discountedPrice -= discountedPrice * (percentageDiscount / 100.0);
        return Math.max(discountedPrice, 0);
    }

    @Override
    public String getDiscountDetails() {
        return "Simple Discount: Flat - " + this.flatDiscount + ", Percentage - " + this.percentageDiscount + "%";
    }
}
