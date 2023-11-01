package com.hotel.discount.interfaces;

public interface IDiscountMgt {
    void applySimpleDiscount(IChargeable chargeable, double flatDiscount, double percentageDiscount, int applierId);

    void applyLoyaltyDiscount(IChargeable chargeable, ILoyaltyStatus loyaltyStatus);
}
