package com.hotel.loyalty.interfaces;
import com.hotel.accounts.entity.GuestAccountEntity;

public interface ILoyaltyObserver {
    void updateLoyalty(GuestAccountEntity account);
}

