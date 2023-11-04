package com.hotel.discount.entity;

import javax.persistence.Entity;

// Decorator interface
@Entity
public abstract class DiscountDecoratorEntity implements IChargeable {
    protected IChargeable chargeable;
    protected double flatDiscount;
    protected double percentageDiscount;

    public DiscountDecoratorEntity(IChargeable chargeable) {
        this.chargeable = chargeable;
    }

    public abstract double getPrice();
    public abstract String getDiscountDetails();
}
