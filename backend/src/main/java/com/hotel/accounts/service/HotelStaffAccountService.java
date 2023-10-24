package com.hotel.accounts.service;

import com.hotel.accounts.entity.HotelStaffAccountEntity;
import com.hotel.accounts.repository.HotelStaffAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean isAdmin(Long id) {
        HotelStaffAccountEntity staff = repository.findById(id).orElse(null);
        return staff != null && "Admin".equalsIgnoreCase(staff.getRole());
    }

    public Optional<HotelStaffAccountEntity> authenticate(String email, String password) {
        // Ensure this method is correctly querying the database
        return repository.findByEmailAndPassword(email, password);
    }
    // other methods if we need
}
