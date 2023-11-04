package com.hotel.discount.service;

import org.springframework.stereotype.Service;

import com.hotel.accounts.repository.AccountRepository;
import com.hotel.discount.interfaces.IDiscountMgt;

@Service
class DiscountMgtService implements IDiscountMgt {
    // Sort out account stuff here
    private final AccountRepository accountRepository;

    DiscountMgtService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Note update account stuff, currently wrong
    @Override
    public void applySimpleDiscount(IChargeable chargeable, double flatDiscount, double percentageDiscount, int applierId) {
        Optional<Account> accountOptional = accountRepository.findById(applierId);
        accountOptional.ifPresent(account -> {
            if (percentageDiscount <= 75 && account.isStaff()) {
                chargeable = new SimpleDiscount(chargeable, flatDiscount, percentageDiscount);
            }
        });
    }

    // Need to update business logic
    @Override
    public void applyLoyaltyDiscount(IChargeable chargeable, ILoyaltyStatus loyaltyStatus) {
        Optional<Account> accountOptional = accountRepository.findById(loyaltyStatus.getUserId());
        accountOptional.ifPresent(account -> {
            if (account.isStaff()) {
                chargeable = new LoyaltyDiscount(chargeable, loyaltyStatus);
            }
        });
    }
}