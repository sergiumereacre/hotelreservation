package com.hotel.discounts.entity;

import javax.persistence.Entity;

import com.hotel.payments.interfaces.IChargeable;

// BaseDecorator (Decorator Pattern)
@Entity
public abstract class DiscountDecoratorEntity implements IChargeable {
    
    protected IChargeable chargeable;
    protected double flatDiscount;
    protected double percentageDiscount;

    public DiscountDecoratorEntity(IChargeable chargeable) {
        this.chargeable = chargeable;
    }

    @Override
    public double getPrice() {
        return chargeable.getPrice();
    }

    @Override
    public String getDiscountDetails() {
        return chargeable.getDiscountDetails();
    }

    //public abstract double getPrice();
    //public abstract String getDiscountDetails();
}
