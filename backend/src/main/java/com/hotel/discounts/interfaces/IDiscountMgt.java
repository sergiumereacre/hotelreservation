package com.hotel.discounts.interfaces;

import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.payments.entity.PaymentEntity;

public interface IDiscountMgt {
    void applySimpleDiscount(PaymentEntity chargeable, double flatDiscount, double percentageDiscount, int applierId);

    void applyLoyaltyDiscount(PaymentEntity chargeable, LoyaltyEntity loyaltyStatus);
}
