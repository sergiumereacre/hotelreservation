package com.hotel.discount.entity;

import com.hotel.loyalty.service.LoyaltyService;

// DiscountRequest.java (Hypothetical DTO class)
public class DiscountRequest {

    private double flatDiscount;
    private double percentageDiscount;
    private int applierId;
    // Assuming that chargeable and loyaltyStatus are simple enough to be serialized directly;
    // otherwise, you would use some form of identifier to look up the relevant objects.
    private IChargeable chargeable;
    private LoyaltyService loyaltyStatus;

    // Default constructor for JSON deserialization
    public DiscountRequest() {
    }

    // Full constructor for all fields
    public DiscountRequest(IChargeable chargeable, double flatDiscount, double percentageDiscount, int applierId, LoyaltyService loyaltyStatus) {
        this.chargeable = chargeable;
        this.flatDiscount = flatDiscount;
        this.percentageDiscount = percentageDiscount;
        this.applierId = applierId;
        this.loyaltyStatus = loyaltyStatus;
    }

    // Getters and setters
    public IChargeable getChargeable() {
        return chargeable;
    }

    public void setChargeable(IChargeable chargeable) {
        this.chargeable = chargeable;
    }

    public double getFlatDiscount() {
        return flatDiscount;
    }

    public void setFlatDiscount(double flatDiscount) {
        this.flatDiscount = flatDiscount;
    }

    public double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public int getApplierId() {
        return applierId;
    }

    public void setApplierId(int applierId) {
        this.applierId = applierId;
    }

    public LoyaltyService getLoyaltyStatus() {
        return loyaltyStatus;
    }

    public void setLoyaltyStatus(LoyaltyService loyaltyStatus) {
        this.loyaltyStatus = loyaltyStatus;
    }

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
