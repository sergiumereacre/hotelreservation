package com.hotel.accounts.service;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // COMMON METHODS

    public AccountEntity saveAccount(AccountEntity account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.save(account);
    }

    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }

    // STAFF SPECIFIC METHODS

    public List<AccountEntity> getAllStaff() {
        return repository.findAllByIsStaff(true);
    }

    public AccountEntity getStaffById(Long id) {
        return repository.findByIdAndIsStaff(id, true).orElse(null);
    }



    public boolean isAdmin(Long id) {
        AccountEntity account = repository.findById(id).orElse(null);
        return account != null && "Admin".equalsIgnoreCase(account.getRole());
    }

    public Optional<AccountEntity> authenticateStaff(String email, String password) {
        AccountEntity staff = repository.findByEmailAndIsStaff(email, true).orElse(null);
        if (staff != null && passwordEncoder.matches(password, staff.getPassword())) {
            return Optional.of(staff);
        }
        return Optional.empty();
    }

    // GUEST SPECIFIC METHODS

    public List<GuestAccountEntity> getAllGuests() {
        return repository.findAllByIsGuest(true);
    }

    public GuestAccountEntity getGuestById(Long id) {
        return repository.findByIdAndIsGuest(id, true).orElse(null);
    }

    public Optional<GuestAccountEntity> authenticateGuest(String email, String password) {
        GuestAccountEntity guest = repository.findByEmailAndIsGuest(email, true).orElse(null);
        if (guest != null && passwordEncoder.matches(password, guest.getPassword())) {
            return Optional.of(guest);
        }
        return Optional.empty();
    }

    public GuestAccountEntity saveGuestAccount(GuestAccountEntity account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.save(account);
    }
}
