package com.hotel.accounts.service;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.repository.AccountRepository;
import com.hotel.loyalty.interfaces.IGuestAccountObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private ApplicationEventPublisher eventPublisher;
    private List<IGuestAccountObserver> observers = new ArrayList<>();

    @Autowired
    public AccountService(PasswordEncoder passwordEncoder, ApplicationEventPublisher eventPublisher) {
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
    }

    // OBSERVER METHODS
    public void addObserver(IGuestAccountObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IGuestAccountObserver observer) {
        observers.remove(observer);
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
        return repository.findAllGuests();
    }

    public GuestAccountEntity getGuestById(Long id) {
        return (GuestAccountEntity) repository.findById(id).orElse(null);
    }

    // Increment the number of stays for the guest account.
    public Optional<GuestAccountEntity> updateNumStays(Long id) {
        GuestAccountEntity guest = repository.findByIdAndIsGuest(id, true).orElse(null);
        if (guest != null) {
            guest.setNumStays(guest.getNumStays() + 1);
            repository.save(guest);
            // Triggers the updateLoyalty method in the LoyaltyService.
            for (IGuestAccountObserver observer : observers) {
                observer.updateLoyalty(guest);
            }
            return Optional.of(guest);
        } else {
            return Optional.empty();
        }
    }
    
    public Optional<GuestAccountEntity> authenticateGuest(String email, String password) {
        GuestAccountEntity guest = repository.findByEmail(email).orElse(null);
        if (guest != null && passwordEncoder.matches(password, guest.getPassword())) {
            return Optional.of(guest);
        }
        return Optional.empty();
    }

    public GuestAccountEntity saveGuestAccount(GuestAccountEntity account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        GuestAccountEntity savedAccount = repository.save(account);
        // Triggers the GuestAccountCreatedEvent.
        eventPublisher.publishEvent(new GuestAccountCreatedEvent(this, account));
        return savedAccount;
    }
}
