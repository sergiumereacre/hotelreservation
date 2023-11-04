package com.hotel.accounts.service;

import com.hotel.accounts.entity.*;
import com.hotel.accounts.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GuestAccountFactory guestAccountFactory;

    @Mock
    private HotelStaffAccountFactory staffAccountFactory;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createGuestAccountShouldReturnGuestAccountEntity() {
        GuestAccountEntity mockGuestAccount = new GuestAccountEntity();
        when(guestAccountFactory.createAccount()).thenReturn(mockGuestAccount);

        GuestAccountEntity result = accountService.createGuestAccount();

        assertNotNull(result);
        verify(guestAccountFactory).createAccount();
    }

    @Test
    void createStaffAccountShouldReturnHotelStaffAccountEntity() {
        HotelStaffAccountEntity mockStaffAccount = new HotelStaffAccountEntity();
        when(staffAccountFactory.createAccount()).thenReturn(mockStaffAccount);

        HotelStaffAccountEntity result = accountService.createStaffAccount();

        assertNotNull(result);
        verify(staffAccountFactory).createAccount();
    }

    @Test
    void saveAccountShouldEncodePasswordAndSaveAccount() {
        AccountEntity dummyAccount = new GuestAccountEntity();
        dummyAccount.setPassword("plainPassword");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(dummyAccount);

        AccountEntity savedAccount = accountService.saveAccount(dummyAccount);

        assertEquals("encodedPassword", savedAccount.getPassword());
        verify(passwordEncoder).encode("plainPassword");
        verify(accountRepository).save(dummyAccount);
    }

    @Test
    void deleteAccount() {
        Long accountId = 1L;
        doNothing().when(accountRepository).deleteById(accountId);
        accountService.deleteAccount(accountId);
        verify(accountRepository).deleteById(accountId);
    }

    @Test
    void getAllStaff() {
        when(accountRepository.findAllByIsStaff(true)).thenReturn(List.of(new HotelStaffAccountEntity()));
        List<AccountEntity> staff = accountService.getAllStaff();
        assertNotNull(staff);
        assertFalse(staff.isEmpty());
        verify(accountRepository).findAllByIsStaff(true);
    }

    @Test
    void getStaffById() {
        Long staffId = 1L;
        when(accountRepository.findByIdAndIsStaff(staffId, true)).thenReturn(Optional.of(new HotelStaffAccountEntity()));
        AccountEntity staff = accountService.getStaffById(staffId);
        assertNotNull(staff);
        verify(accountRepository).findByIdAndIsStaff(staffId, true);
    }

    @Test
    void isAdmin() {
        Long adminId = 1L;
        AccountEntity admin = new HotelStaffAccountEntity();
        admin.setRole("Admin");
        when(accountRepository.findById(adminId)).thenReturn(Optional.of(admin));
        assertTrue(accountService.isAdmin(adminId));
        verify(accountRepository).findById(adminId);
    }

    @Test
    void authenticateStaff() {
        String email = "staff@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        HotelStaffAccountEntity staff = new HotelStaffAccountEntity();
        staff.setEmail(email);
        staff.setPassword(encodedPassword);

        when(accountRepository.findByEmailAndIsStaff(email, true)).thenReturn(Optional.of(staff));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);

        Optional<AccountEntity> authenticatedStaff = accountService.authenticateStaff(email, password);
        assertTrue(authenticatedStaff.isPresent());
        assertEquals(email, authenticatedStaff.get().getEmail());
    }

    @Test
    void getAllGuests() {
        when(accountRepository.findAllGuests()).thenReturn(List.of(new GuestAccountEntity()));
        List<GuestAccountEntity> guests = accountService.getAllGuests();
        assertNotNull(guests);
        assertFalse(guests.isEmpty());
        verify(accountRepository).findAllGuests();
    }

    @Test
    void getGuestById() {
        Long guestId = 1L;
        when(accountRepository.findById(guestId)).thenReturn(Optional.of(new GuestAccountEntity()));
        GuestAccountEntity guest = accountService.getGuestById(guestId);
        assertNotNull(guest);
        verify(accountRepository).findById(guestId);
    }

    @Test
    void updateNumStays() {
        Long guestId = 1L;
        GuestAccountEntity guest = new GuestAccountEntity();
        guest.setNumStays(0);

        when(accountRepository.findById(guestId)).thenReturn(Optional.of(guest));
        when(accountRepository.save(any(GuestAccountEntity.class))).thenReturn(guest);

        Optional<GuestAccountEntity> updatedGuest = accountService.updateNumStays(guestId);
        assertTrue(updatedGuest.isPresent());
        assertEquals(1, updatedGuest.get().getNumStays());
    }

    @Test
    void authenticateGuest() {
        String email = "guest@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        GuestAccountEntity guest = new GuestAccountEntity();
        guest.setEmail(email);
        guest.setPassword(encodedPassword);

        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(guest));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);

        Optional<GuestAccountEntity> authenticatedGuest = accountService.authenticateGuest(email, password);
        assertTrue(authenticatedGuest.isPresent());
        assertEquals(email, authenticatedGuest.get().getEmail());
    }

    @Test
    void saveGuestAccount() {
        GuestAccountEntity guest = new GuestAccountEntity();
        guest.setEmail("guest@example.com");
        guest.setPassword("password");
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(guest.getPassword())).thenReturn(encodedPassword);
        when(accountRepository.save(guest)).thenReturn(guest);

        GuestAccountEntity savedGuest = accountService.saveGuestAccount(guest);
        assertNotNull(savedGuest);
        assertEquals(encodedPassword, savedGuest.getPassword());
    }
}