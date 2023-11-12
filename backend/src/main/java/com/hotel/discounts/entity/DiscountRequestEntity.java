package com.hotel.discounts.entity;

import com.hotel.loyalty.entity.LoyaltyEntity;
// import com.hotel.payments.entity.ChargeableEntity;
import com.hotel.payments.entity.PaymentEntity;

// DiscountRequest.java (Hypothetical DTO class)
// @Entity
public class DiscountRequestEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    private double flatDiscount;
    private double percentageDiscount;
    private int applierId;
    // Assuming that chargeable and loyaltyStatus are simple enough to be serialized directly;
    // otherwise, you would use some form of identifier to look up the relevant objects.
    private PaymentEntity chargeable;
    // private LoyaltyService loyaltyStatus;
    private LoyaltyEntity loyaltyStatus;

    // Default constructor for JSON deserialization
    public DiscountRequestEntity() {
    }

    // Full constructor for all fields
    // public DiscountRequest(IChargeable chargeable, double flatDiscount, double percentageDiscount, int applierId, LoyaltyService loyaltyStatus) {
    //     this.chargeable = chargeable;
    //     this.flatDiscount = flatDiscount;
    //     this.percentageDiscount = percentageDiscount;
    //     this.applierId = applierId;
    //     this.loyaltyStatus = loyaltyStatus;
    // }

     public DiscountRequestEntity(PaymentEntity chargeable, double flatDiscount, double percentageDiscount, int applierId, LoyaltyEntity loyaltyStatus) {
        this.chargeable = chargeable;
        this.flatDiscount = flatDiscount;
        this.percentageDiscount = percentageDiscount;
        this.applierId = applierId;
        this.loyaltyStatus = loyaltyStatus;
    }

    // Getters and setters
    public PaymentEntity getChargeable() {
        return chargeable;
    }

    public void setChargeable(PaymentEntity chargeable) {
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

    // public LoyaltyService getLoyaltyStatus() {
    //     return loyaltyStatus;
    // }

     public LoyaltyEntity getLoyaltyStatus() {
        return loyaltyStatus;
    }

    // public void setLoyaltyStatus(LoyaltyService loyaltyStatus) {
    //     this.loyaltyStatus = loyaltyStatus;
    // }

      public void setLoyaltyStatus(LoyaltyEntity loyaltyStatus) {
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
