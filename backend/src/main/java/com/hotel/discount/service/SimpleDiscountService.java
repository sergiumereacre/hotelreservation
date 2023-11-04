package com.hotel.discount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.discount.entity.DiscountDecoratorEntity;
import com.hotel.discount.interfaces.IChargeable;
import com.hotel.discount.interfaces.IDiscountMgt;
import com.hotel.discount.interfaces.ILoyaltyStatus;

// Concrete decorators SimpleDiscount and LoyaltyDiscount
@Service
public class SimpleDiscountService extends DiscountDecoratorEntity implements IDiscountMgt {

    @Autowired
    public SimpleDiscountService(IDiscountMgt discount) {
        super(discount);
    }

    @Override
    public void applySimpleDiscount(IChargeable chargeable, double flatDiscount, double percentageDiscount, int applierId) {
        // TODO Add simple discount logic
        // price * discountRate
    }

    @Override
    public void applyLoyaltyDiscount(IChargeable chargeable, ILoyaltyStatus loyaltyStatus) {
        // TODO Add loyalty discount logic
        // check what loyalty status they have, then apply the discount
        // price * discountRate
    }
}