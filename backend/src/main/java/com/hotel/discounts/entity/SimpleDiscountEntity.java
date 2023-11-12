package com.hotel.discounts.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

// import com.hotel.payments.entity.ChargeableEntity;
import com.hotel.payments.entity.PaymentEntity;

// ConcreteDecorator (Decorator Pattern)
// @Component
@Entity
@Table(name = "simple_discount")
public class SimpleDiscountEntity extends DiscountDecoratorEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long discountId;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChargeDetails'");
    }

    @Override
    public void setIsPaid(boolean isPaid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIsPaid'");
    }

    @Override
    public boolean getIsPaid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIsPaid'");
    }
}
