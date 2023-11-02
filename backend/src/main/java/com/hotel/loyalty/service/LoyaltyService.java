package com.hotel.loyalty.service;

import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.loyalty.interfaces.IGuestAccountObserver;
import com.hotel.loyalty.repository.LoyaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyService implements IGuestAccountObserver {
    @Autowired
    private LoyaltyRepository repository;

    // Return loyalty type of the guest account id.
    public String getLoyaltyType(Long id) {
        LoyaltyEntity loyaltyEntity = repository.findById(id).orElse(null);
        if (loyaltyEntity != null) {
            Loyalty loyalty = loyaltyEntity.getType();
            return loyalty.name();
        } else {
            return null;
        }
    }

    // Return loyalty of the guest account id.
    public LoyaltyEntity getLoyaltyEntity(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Save loyalty in the repository.
    public void saveLoyalty(LoyaltyEntity loyalty) {
        repository.save(loyalty);
    }

    // Delete loyalty from the repository.
    public void deleteLoyalty(Long id) {
        repository.deleteById(id);
    }

    // Update loyalty of the guest account.
    @Override
    public void updateLoyalty(GuestAccountEntity account) {
        int numStays = account.getNumStays();
        Loyalty loyalty;

        if (numStays >= 10) {
            loyalty = Loyalty.GOLD;
        } else if (numStays >= 5) {
            loyalty = Loyalty.SILVER;
        } else if (numStays >= 1) {
            loyalty = Loyalty.BRONZE;
        } else {
            loyalty = Loyalty.STANDARD;
        }

        LoyaltyEntity loyaltyEntity = repository.findById(account.getId()).orElse(null);
        if (loyaltyEntity != null) {
            loyaltyEntity.setType(loyalty);
        } else {
            loyaltyEntity = new LoyaltyEntity();
            loyaltyEntity.setGuestAccount(account);
            loyaltyEntity.setType(loyalty);
        }

        repository.save(loyaltyEntity);
    }
}
