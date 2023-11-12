package com.hotel.discounts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.hotel.loyalty.entity.LoyaltyEntity;
// import com.hotel.payments.entity.ChargeableEntity;
import com.hotel.payments.entity.PaymentEntity;

// ConcreteDecorator (Decorator Pattern)
// @Component
@Entity
@Table(name = "loyalty_discount")
public class LoyaltyDiscountEntity extends DiscountDecoratorEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long discountId;

    @OneToOne(fetch = javax.persistence.FetchType.LAZY)
    private LoyaltyEntity loyaltyStatus;

    public LoyaltyDiscountEntity(PaymentEntity chargeable, LoyaltyEntity loyaltyStatus) {
        super(chargeable);
        this.loyaltyStatus = loyaltyStatus;
    }

    @Override
    public double getPrice() {
        double originalPrice = chargeable.getPrice();
        // double discountPercentage = loyaltyStatus.getDiscount();

        double discountPercentage = getDiscountPercentage();
        double discountedPrice = originalPrice - (originalPrice * (discountPercentage / 100.0));
        return Math.max(discountedPrice, 0);
    }

    @Override
    public String getDiscountDetails() {
        // return "Loyalty Discount: " + loyaltyStatus.getType() + " - " +
        // loyaltyStatus.getDiscount() + "%";
        return "Loyalty Discount: " + loyaltyStatus.getType() + " - " + getDiscountPercentage() + "%";

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
