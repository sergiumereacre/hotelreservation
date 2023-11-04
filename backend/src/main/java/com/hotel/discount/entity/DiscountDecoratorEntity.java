package com.hotel.discount.entity;

import javax.persistence.Entity;

// Abstract decorator class
@Entity
public abstract class DiscountDecoratorEntity implements IChargeable {
    
    protected IChargeable chargeable;
    protected double flatDiscount;
    protected double percentageDiscount;

    DiscountDecoratorEntity(IChargeable chargeable) {
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
