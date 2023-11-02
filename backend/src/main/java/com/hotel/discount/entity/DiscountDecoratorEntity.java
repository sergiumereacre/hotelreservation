package com.hotel.discount.entity;

import javax.persistence.Entity;

import com.hotel.discount.interfaces.IDiscountMgt;

// Base for the decorators
@Entity
public abstract class DiscountDecoratorEntity implements IDiscountMgt {
    protected IDiscountMgt discount;

    public DiscountDecoratorEntity(IDiscountMgt discount) {
        this.discount = discount;
    }

    @Override
    public double applySimpleDiscount() {

    }

    @Override
    public double applyLoyaltyDiscount() {

    }
}
