package com.hotel.discount.interfaces;

// Component (Decorator Pattern)
public interface IDiscountMgt {
    // Waiting for IChargeable interface
    void applySimpleDiscount(IChargeable chargeable, double flatDiscount, double percentageDiscount, int applierId);

    // Waiting for IChargeable and ILoyaltyStatus interfaces
    void applyLoyaltyDiscount(IChargeable chargeable, ILoyaltyStatus loyaltyStatus);
}
