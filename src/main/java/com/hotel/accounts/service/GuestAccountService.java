package com.hotel.accounts.service;

import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.repository.GuestAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestAccountService {


    @Autowired
    private GuestAccountRepository repository;

    public List<GuestAccountEntity> getAllGuests() {
        return repository.findAll();
    }

    public GuestAccountEntity getGuestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public GuestAccountEntity saveGuest(GuestAccountEntity guestAccount) {
        return repository.save(guestAccount);
    }

    public void deleteGuest(Long id) {
        repository.deleteById(id);
    }

    // add more methods when we need to
}