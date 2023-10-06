package com.hotel.accounts.service;

import com.hotel.accounts.entity.HotelStaffAccountEntity;
import com.hotel.accounts.repository.HotelStaffAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelStaffAccountService {

    @Autowired
    private HotelStaffAccountRepository repository;

    public List<HotelStaffAccountEntity> getAllStaff() {
        return repository.findAll();
    }

    public HotelStaffAccountEntity getStaffById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public HotelStaffAccountEntity saveStaff(HotelStaffAccountEntity staffAccount) {
        return repository.save(staffAccount);
    }

    public void deleteStaff(Long id) {
        repository.deleteById(id);
    }

    // other methods if we need
}
