package com.hotel.discount.entity;

public class SimpleDiscountEntity {
    private double flatDiscount;
    private double percentageDiscount;

    public SimpleDiscountEntity(double flatDiscount, double percentageDiscount) {
        this.flatDiscount = flatDiscount;
        this.percentageDiscount = percentageDiscount;
    }

    public double getPrice() {
        return 0;
    }

    public String getDiscountDetails() {
        return "";
    }
}
