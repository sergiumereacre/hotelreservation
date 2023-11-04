package com.hotel.accounts.repository;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.entity.HotelStaffAccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    private AccountRepository mockAccountRepository;

    private HotelStaffAccountEntity staffAccountEntity;
    private GuestAccountEntity guestAccountEntity;

    @BeforeEach
    public void setUp() {
        staffAccountEntity = new HotelStaffAccountEntity();
        staffAccountEntity.setId(1L);
        staffAccountEntity.setName("Staff User");
        staffAccountEntity.setEmail("staff@example.com");
        staffAccountEntity.setPassword("password123");
        staffAccountEntity.setStaff(true);

        guestAccountEntity = new GuestAccountEntity();
        guestAccountEntity.setId(2L);
        guestAccountEntity.setName("Guest User");
        guestAccountEntity.setEmail("guest@example.com");
        guestAccountEntity.setPassword("password123");
        guestAccountEntity.setNumStays(5);
    }

    @Test
    public void testFindAllByIsStaff_WhenIsStaff() {
        // Setup mock repo
        when(mockAccountRepository.findAllByIsStaff(true)).thenReturn(Arrays.asList(staffAccountEntity));
        List<AccountEntity> foundAccounts = accountRepository.findAllByIsStaff(true);
        // Validate response
        assertNotNull(foundAccounts);
        assertFalse(foundAccounts.isEmpty());
        assertTrue(foundAccounts.get(0) instanceof HotelStaffAccountEntity);
        assertTrue(foundAccounts.get(0).isStaff());
    }

    @Test
    public void testFindByIdAndIsStaff_WhenFound() {
        // Setup mock repo
        when(mockAccountRepository.findByIdAndIsStaff(1L, true)).thenReturn(Optional.of(staffAccountEntity));
        Optional<AccountEntity> foundAccount = accountRepository.findByIdAndIsStaff(1L, true);

        // Validate response
        assertTrue(foundAccount.isPresent());
        assertEquals(staffAccountEntity, foundAccount.get());
    }

    @Test
    public void testFindAllGuests() {
        // Setup mock repo
        when(mockAccountRepository.findAllGuests()).thenReturn(Arrays.asList(guestAccountEntity));
        List<GuestAccountEntity> foundGuests = accountRepository.findAllGuests();
        // Validate response
        assertNotNull(foundGuests);
        assertFalse(foundGuests.isEmpty());
        assertTrue(foundGuests.get(0) instanceof GuestAccountEntity);
    }

    @Test
    public void testFindByEmail() {
        // Setup mock repo
        when(mockAccountRepository.findByEmail("guest@example.com")).thenReturn(Optional.of(guestAccountEntity));
        Optional<GuestAccountEntity> foundGuest = accountRepository.findByEmail("guest@example.com");
        // Validate response
        assertTrue(foundGuest.isPresent());
        assertEquals(guestAccountEntity.getEmail(), foundGuest.get().getEmail());
    }
}
