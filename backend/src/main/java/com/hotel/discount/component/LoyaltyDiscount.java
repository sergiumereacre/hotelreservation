package com.hotel.discount.component;

import org.springframework.stereotype.Component;

import com.hotel.discount.entity.DiscountDecoratorEntity;

@Component
class LoyaltyDiscount extends DiscountDecoratorEntity {
    private ILoyaltyStatus loyaltyStatus;

    LoyaltyDiscount(IChargeable chargeable, ILoyaltyStatus loyaltyStatus) {
        super(chargeable);
        this.loyaltyStatus = loyaltyStatus;
    }

    @Override
    public double getPrice() {
        double originalPrice = chargeable.getPrice();
        double discountPercentage = loyaltyStatus.getDiscount();
        double discountedPrice = originalPrice - (originalPrice * (discountPercentage / 100.0));
        return Math.max(discountedPrice, 0);
    }

    @Override
    public String getDiscountDetails() {
        return "Loyalty Discount: " + loyaltyStatus.getLoyaltyType() + " - " + loyaltyStatus.getDiscount() + "%";
    }
}
