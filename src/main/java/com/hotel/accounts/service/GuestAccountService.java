package com.hotel.accounts.service;

import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.repository.GuestAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestAccountService {


    @Autowired
    private GuestAccountRepository repository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public GuestAccountEntity saveGuest(GuestAccountEntity guestAccount) {
        guestAccount.setPassword(passwordEncoder.encode(guestAccount.getPassword()));
        return repository.save(guestAccount);
    }

    public List<GuestAccountEntity> getAllGuests() {
        return repository.findAll();
    }

    public GuestAccountEntity getGuestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteGuest(Long id) {
        repository.deleteById(id);
    }


    // or staffAccount.setPassword(encryptedPassword);
    // add more methods when we need to
}