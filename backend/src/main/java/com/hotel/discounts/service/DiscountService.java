package com.hotel.discounts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.repository.AccountRepository;
import com.hotel.discounts.entity.DiscountDecoratorEntity;
import com.hotel.discounts.entity.LoyaltyDiscountEntity;
import com.hotel.discounts.entity.SimpleDiscountEntity;
import com.hotel.discounts.interfaces.IDiscountMgt;
import com.hotel.discounts.repository.DiscountRepository;
import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.payments.entity.PaymentEntity;

// Note: Creation of percentage discounts by staff (limited to a certain percentage off) for bookings. Only one discount can be applied per reservation.

@Service
public class DiscountService implements IDiscountMgt {
    private final AccountRepository accountRepository;

    DiscountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public void applySimpleDiscount(PaymentEntity chargeable, double flatDiscount, double percentageDiscount, int applierId) {
        Optional<AccountEntity> accountOptional = accountRepository.findById((long) applierId);
        accountOptional.ifPresent(account -> {
            if (percentageDiscount <= 75 && account.isStaff()) {
                DiscountDecoratorEntity discount = new SimpleDiscountEntity(chargeable, flatDiscount, percentageDiscount);
                discountRepository.save(discount);
            }
        });
    }

    @Override
    public void applyLoyaltyDiscount(PaymentEntity chargeable, LoyaltyEntity loyaltyStatus) {
        Optional<AccountEntity> accountOptional = accountRepository.findById(loyaltyStatus.getGuestAccount().getId());
        accountOptional.ifPresent(account -> {
            if (account.isStaff()) {
                DiscountDecoratorEntity discount = new LoyaltyDiscountEntity(chargeable, loyaltyStatus);
                discountRepository.save(discount);
            }
        });
    }
}