package com.hotel.loyalty.service;

import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.loyalty.entity.LoyaltyEntity;
import com.hotel.loyalty.interfaces.ILoyaltyObserver;
import com.hotel.loyalty.repository.LoyaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyService {
    @Autowired
    private LoyaltyRepository loyaltyRepository;

    private List<ILoyaltyObserver> observers = new ArrayList<>();

    public void addObserver(ILoyaltyObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ILoyaltyObserver observer) {
        observers.remove(observer);
    }

    public void updateLoyalty(GuestAccountEntity account) {
        int numStays = account.getNumStays();
        Loyalty loyalty;

        if (numStays >= 10) {
            loyalty = Loyalty.GOLD;
        } else if (numStays >= 5) {
            loyalty = Loyalty.SILVER;
        } else {
            loyalty = Loyalty.BRONZE;
        }

        LoyaltyEntity loyaltyEntity = loyaltyRepository.findByAccount(account);
        if (loyaltyEntity != null) {
            loyaltyEntity.setLoyalty(loyalty);
        } else {
            loyaltyEntity = new LoyaltyEntity();
            loyaltyEntity.setAccount(account);
            loyaltyEntity.setLoyalty(loyalty);
        }

        loyaltyRepository.save(loyaltyEntity);

        // Notify all observers
        for (LoyaltyObserver observer : observers) {
            observer.updateLoyalty(account);
        }
    }
}

