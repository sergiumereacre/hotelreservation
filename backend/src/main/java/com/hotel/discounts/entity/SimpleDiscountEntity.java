package com.hotel.discounts.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hotel.payments.entity.PaymentEntity;

// ConcreteDecorator (Decorator Design Pattern)
@Entity
@Table(name = "simple_discount")
public class SimpleDiscountEntity extends DiscountDecoratorEntity {
    public SimpleDiscountEntity(PaymentEntity chargeable, double flatDiscount, double percentageDiscount) {
        super(chargeable);
        this.flatDiscount = flatDiscount;
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public double getPrice() {
        double originalPrice = chargeable.getPrice();
        double discountedPrice = originalPrice - flatDiscount;
        discountedPrice -= discountedPrice * (percentageDiscount / 100.0);
        return Math.max(discountedPrice, 0);
    }

    @Override
    public String getDiscountDetails() {
        return "Simple Discount: Flat - " + this.flatDiscount + ", Percentage - " + this.percentageDiscount + "%";
    }

    @Override
    public String getChargeDetails() {
        return "Price: " + getPrice() + ", Discount Details: " + getDiscountDetails();
    }
}
