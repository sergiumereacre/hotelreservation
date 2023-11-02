package com.hotel.discount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.discount.entity.DiscountDecoratorEntity;
import com.hotel.discount.interfaces.IChargeable;
import com.hotel.discount.interfaces.IDiscountMgt;

// Concrete decorators SimpleDiscount and LoyaltyDiscount
@Service
public class SimpleDiscountService extends DiscountDecoratorEntity {

    @Autowired
    public SimpleDiscountService(IDiscountMgt discount) {
        super(discount);
    }

    @Override
    public void applySimpleDiscount(IChargeable chargeable, double flatDiscount, double percentageDiscount, int applierId) {
        // TODO Add simple discount logic
        return;
    }

}