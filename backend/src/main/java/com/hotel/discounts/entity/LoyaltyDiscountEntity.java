package com.hotel.discounts.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.payments.entity.PaymentEntity;

// ConcreteDecorator (Decorator Design Pattern)
@Entity
@Table(name = "loyalty_discount")
public class LoyaltyDiscountEntity extends DiscountDecoratorEntity {
    @OneToOne
    private LoyaltyEntity loyaltyStatus;

    public LoyaltyDiscountEntity(PaymentEntity chargeable, LoyaltyEntity loyaltyStatus) {
        super(chargeable);
        this.loyaltyStatus = loyaltyStatus;
    }

    @Override
    public double getPrice() {
        double originalPrice = chargeable.getPrice();

        double discountPercentage = getDiscountPercentage();
        double discountedPrice = originalPrice - (originalPrice * (discountPercentage / 100.0));
        return Math.max(discountedPrice, 0);
    }

    @Override
    public String getDiscountDetails() {
        return "Loyalty Discount: " + loyaltyStatus.getType() + " - " + getDiscountPercentage() + "%";
    }

    @Override
    public String getChargeDetails() {
        return "Price: " + getPrice() + ", Discount Details: " + getDiscountDetails();
    }

    private double getDiscountPercentage() {
        double discountPercentage = 0.0;

        switch (this.loyaltyStatus.getType()) {
            case BRONZE:
                discountPercentage = 5.0;
                break;
            case SILVER:
                discountPercentage = 10.0;
                break;
            case GOLD:
                discountPercentage = 15.0;
                break;
            default:
                discountPercentage = 0.0;
                break;
        }

        return discountPercentage;
    }
}
