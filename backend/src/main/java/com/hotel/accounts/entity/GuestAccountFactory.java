package com.hotel.accounts.entity;

import org.springframework.stereotype.Component;

@Component
public class GuestAccountFactory implements AccountFactory {
    @Override
    public AccountEntity createAccount() {
        return new GuestAccountEntity();
    }
}
