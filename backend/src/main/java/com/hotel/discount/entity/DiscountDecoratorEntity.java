package com.hotel.discount.entity;

import javax.persistence.Entity;

import com.hotel.discount.interfaces.IDiscountMgt;

// Base for the decorators
@Entity
public abstract class DiscountDecoratorEntity implements IDiscountMgt {
    protected IDiscountMgt discount;
    protected double flatDiscount;
    protected double percentageDiscount;

    public DiscountDecoratorEntity(IDiscountMgt discount) {
        this.discount = discount;
    }
}
