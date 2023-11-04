package com.hotel.config;
import com.hotel.accounts.service.AccountService;
import com.hotel.loyalty.service.LoyaltyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class LoyaltyServiceRegistrationTest {

    @Mock
    private AccountService accountService;

    @Mock
    private LoyaltyService loyaltyService;

    @InjectMocks
    private LoyaltyServiceRegistration loyaltyServiceRegistration;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loyaltyServiceRegistrationTest() {
        verify(accountService).addObserver(loyaltyService);
    }
}

