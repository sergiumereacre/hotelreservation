package com.hotel.discounts.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotel.payments.entity.PaymentEntity;

// BaseDecorator (Decorator Design Pattern)
@Entity
@Table(name = "discount_decorator_entity")
public abstract class DiscountDecoratorEntity extends PaymentEntity {
    @OneToOne
    @JoinColumn(name = "payment_ref", referencedColumnName = "payment_ref")
    protected PaymentEntity chargeable;

    protected double flatDiscount;
    protected double percentageDiscount;

    public DiscountDecoratorEntity(PaymentEntity chargeable) {
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
}
