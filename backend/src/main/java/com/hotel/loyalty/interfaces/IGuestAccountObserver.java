package com.hotel.loyalty.interfaces;
import com.hotel.accounts.entity.GuestAccountEntity;

public interface IGuestAccountObserver {
    void updateLoyalty(GuestAccountEntity account);
}

