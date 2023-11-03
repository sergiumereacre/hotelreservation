package com.hotel.accounts.entity;

import org.springframework.stereotype.Component;

@Component
public class HotelStaffAccountFactory implements AccountFactory {
    @Override
    public AccountEntity createAccount() {
        return new HotelStaffAccountEntity();
    }
}