package com.hotel.discount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.discount.entity.DiscountDecoratorEntity;
import com.hotel.discount.interfaces.IChargeable;
import com.hotel.discount.interfaces.IDiscountMgt;

// Concrete decorators SimpleDiscount and LoyaltyDiscount
@Service
public class LoyaltyDiscountService extends DiscountDecoratorEntity {

    @Autowired
    public LoyaltyDiscountService(IDiscountMgt discount) {
        super(discount);
    }

    @Override
    public void applyLoyaltyDiscount(IChargeable chargeable, ILoyaltyStatus loyaltyStatus) {
        // TODO Add loyalty discount logic
        // check what loyalty status they have, then apply the discount
        // price * discountRate
    }
}
