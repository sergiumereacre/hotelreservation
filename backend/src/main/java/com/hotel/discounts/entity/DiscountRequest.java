package com.hotel.discounts.entity;

import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.payments.entity.PaymentEntity;

import lombok.Data;

@Data
public class DiscountRequest {
    private Long discountId;

    private double flatDiscount;
    private double percentageDiscount;
    private int applierId;

    private PaymentEntity chargeable;
    private LoyaltyEntity loyaltyStatus;

    // toString method for logging and debugging purposes
    @Override
    public String toString() {
        return "DiscountRequest{" +
                "flatDiscount=" + flatDiscount +
                ", percentageDiscount=" + percentageDiscount +
                ", applierId=" + applierId +
                ", chargeable=" + chargeable +
                ", loyaltyStatus=" + loyaltyStatus +
                '}';
    }
}
