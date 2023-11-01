package com.hotel.discount.entity;

public class LoyaltyDiscountEntity {
    private double flatDiscount;
    private double percentageDiscount;
    private String loyaltyType;

    public LoyaltyDiscountEntity(double flatDiscount, double percentageDiscount, String loyaltyType) {
        this.flatDiscount = flatDiscount;
        this.percentageDiscount = percentageDiscount;
        this.loyaltyType = loyaltyType;
    }

    public double getPrice() {
        return 0;
    }

    public String getDiscountDetails() {
        return "";
    }
}
