package com.hotel.discounts.interfaces;

import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.payments.entity.PaymentEntity;

// Component (Decorator Pattern)
public interface IDiscountMgt {
    // Waiting for IChargeable interface
    void applySimpleDiscount(PaymentEntity chargeable, double flatDiscount, double percentageDiscount, int applierId);

    // Waiting for IChargeable and ILoyaltyStatus interfaces
    void applyLoyaltyDiscount(PaymentEntity chargeable, LoyaltyEntity loyaltyStatus);
}
