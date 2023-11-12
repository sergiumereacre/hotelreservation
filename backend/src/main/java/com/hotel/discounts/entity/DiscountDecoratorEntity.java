package com.hotel.discounts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// import com.hotel.payments.entity.ChargeableEntity;
import com.hotel.payments.entity.PaymentEntity;

// BaseDecorator (Decorator Pattern)
// @Entity
// @Table(name = "discount_decorator_entity")
// @MappedSuperclass   
@Entity
@Table(name = "discount_decorator_entity")
public abstract class DiscountDecoratorEntity extends PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;
    

    @OneToOne(fetch = javax.persistence.FetchType.LAZY)
    @JoinColumn(name = "payment_ref", referencedColumnName = "chargeable_id")
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

    //public abstract double getPrice();
    //public abstract String getDiscountDetails();
}
