package com.hotel.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.loyalty.service.LoyaltyService;

// This class is responsible for listening to the GuestAccountCreatedEvent and creating a LoyaltyEntity.
@Component
public class GuestAccountCreationListener {

    private final LoyaltyService loyaltyService;

    // Inject the LoyaltyService into the constructor.
    @Autowired
    public GuestAccountCreationListener(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @EventListener
    public void handleGuestAccountCreation(GuestAccountCreatedEvent event) {
        // Create a LoyaltyEntity and associate it with the created GuestAccountEntity
        LoyaltyEntity loyalty = new LoyaltyEntity();
        loyalty.setGuestAccount(event.getGuestAccount());
        
        // Save the LoyaltyEntity to the LoyaltyRepository
        loyaltyService.saveLoyalty(loyalty);
    }
}

