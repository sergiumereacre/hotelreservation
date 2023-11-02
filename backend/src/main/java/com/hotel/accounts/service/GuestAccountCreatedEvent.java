package com.hotel.accounts.service;

import org.springframework.context.ApplicationEvent;
import com.hotel.accounts.entity.GuestAccountEntity;

// This class is responsible for creating a GuestAccountCreatedEvent.
public class GuestAccountCreatedEvent extends ApplicationEvent {

    // The GuestAccountEntity that was created.
    private final GuestAccountEntity guestAccount;

    // Create a new GuestAccountCreatedEvent.
    public GuestAccountCreatedEvent(Object source, GuestAccountEntity guestAccount) {
        super(source);
        this.guestAccount = guestAccount;
    }

    // Get the GuestAccountEntity that was created.
    public GuestAccountEntity getGuestAccount() {
        return guestAccount;
    }
}
