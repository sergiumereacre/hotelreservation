package com.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hotel.accounts.service.AccountService;

import com.hotel.loyalty.service.LoyaltyService;

@Configuration
public class LoyaltyServiceRegistration {

    private final AccountService accountService;
    private final LoyaltyService loyaltyService;

    @Autowired
    public LoyaltyServiceRegistration(AccountService accountService, LoyaltyService loyaltyService) {
        this.accountService = accountService;
        this.loyaltyService = loyaltyService;
        accountService.addObserver(loyaltyService);
    }
}


