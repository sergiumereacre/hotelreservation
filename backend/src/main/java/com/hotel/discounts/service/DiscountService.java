package com.hotel.discounts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.repository.AccountRepository;
import com.hotel.discounts.interfaces.IDiscountMgt;
import com.hotel.discounts.repository.DiscountRepository;
import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.payments.entity.PaymentEntity;
import com.hotel.discounts.entity.DiscountDecoratorEntity;
import com.hotel.discounts.entity.LoyaltyDiscountEntity;
import com.hotel.discounts.entity.SimpleDiscountEntity;

// Note: Creation of percentage discounts by staff (limited to a certain percentage off) for bookings. Only one discount can be applied per reservation. 

// ConcreteComponent (Decorator Pattern)
@Service
public class DiscountService implements IDiscountMgt {
    // Sort out account stuff here
    private final AccountRepository accountRepository;

    DiscountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    private DiscountRepository discountRepository;

    // Note update account stuff, currently wrong
    @Override
    public void applySimpleDiscount(PaymentEntity chargeable, double flatDiscount, double percentageDiscount, int applierId) {
        Optional<AccountEntity> accountOptional = accountRepository.findById((long) applierId);
        accountOptional.ifPresent(account -> {
            if (percentageDiscount <= 75 && account.isStaff()) {
                // chargeable = new SimpleDiscount(chargeable, flatDiscount, percentageDiscount);

                DiscountDecoratorEntity discount = new SimpleDiscountEntity(chargeable, flatDiscount, percentageDiscount);
                // di
                discountRepository.save(discount);
            }
        });
    }

    // Need to update business logic
    @Override
    public void applyLoyaltyDiscount(PaymentEntity chargeable, LoyaltyEntity loyaltyStatus) {
        Optional<AccountEntity> accountOptional = accountRepository.findById(loyaltyStatus.getGuestAccount().getId());
        accountOptional.ifPresent(account -> {
            if (account.isStaff()) {
                // chargeable = new LoyaltyDiscount(chargeable, loyaltyStatus);

                DiscountDecoratorEntity discount = new LoyaltyDiscountEntity(chargeable, loyaltyStatus);

                discountRepository.save(discount);
            }
        });
    }
}